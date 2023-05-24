package com.chb.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyHandler extends ChannelInboundHandlerAdapter {

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf byteBuf = (ByteBuf) msg;
			log.info("收到客户端" + ctx.channel().remoteAddress() + "发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
			// ctx.fireChannelRead(msg);
			// 获取到线程池eventLoop，添加线程，执行
//	        ctx.channel().eventLoop().execute(new Runnable() {
//	            @Override
//	            public void run() {
//	                try {
//	                    //长时间操作，不至于长时间的业务操作导致Handler阻塞
//	                    Thread.sleep(1000);
//	                    log.info("长时间的业务处理");
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        });

			ByteBuf out = Unpooled.copiedBuffer("server response message : " + byteBuf.toString(CharsetUtil.UTF_8),
					CharsetUtil.UTF_8);
			ChannelFuture future = ctx.writeAndFlush(out);
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) {
					if (future.isSuccess()) {
						log.info("Server response success.");
					} else {
						log.info("Server response failed.");
						future.cause().printStackTrace();
					}
					future.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
				}
			});

		} finally {
			// 釋放msg
			ReferenceCountUtil.release(msg);
		}
	}

	/*
	 * Netty 接受電文訊息通知
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("Netty 內容讀取完成");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("Netty 連線成功");
		ctx.fireChannelActive();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("客戶端與服務端連線關閉");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("讀取發生異常");
		ctx.fireExceptionCaught(cause);
		// ctx.close();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		super.handlerAdded(ctx);
	}
}

package com.chb.utils;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyControl {

	public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
	public int TIME_OUT = 5000;

	private String encoding = "UTF-8"; // cp937 utf-8
	private String IP;
	private int SendPort;
	private int ReadPort;
	private ChannelFuture serverFuture;
	private ChannelFuture clientRed;

	public NettyControl(String IP, int SendPort, int ReadPort) {
		this.IP = IP;
		this.SendPort = SendPort;
		this.ReadPort = ReadPort;
	}

	public void serverSend(){
		EventLoopGroup acceptGroup = new NioEventLoopGroup();
		EventLoopGroup ioGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverbootstrap = new ServerBootstrap();
			serverbootstrap.group(acceptGroup, ioGroup).channel(NioServerSocketChannel.class)// 指定使用 java 的NioServerSocketChannel
					.childHandler(new ChannelInitializer<SocketChannel>() { // 創建 IOThread 的 pipeline

						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {

							// 增設Header
							// socketChannel.pipeline()
							// .addLast(new StringDecoder())
							// .addLast(new StringEncoder()) // 添加echo Handler
							// .addLast(new EchoHandler());

							// 處理server傳來的訊息
							socketChannel.pipeline().addLast("Handler", new IdleStateHandler(0, 3, 0, TimeUnit.SECONDS));
							socketChannel.pipeline().addLast(new NettyHandler());

						}
					}).option(ChannelOption.SO_BACKLOG, 128) // server socket config backlog
						.childOption(ChannelOption.SO_KEEPALIVE, true); // client socket config 设置 keepalive = true

			log.info("Netty Start");
			// 綁定窗口,開始接收進來的連接
			serverFuture = serverbootstrap.bind(SendPort).sync();
			// 服務器連接關閉
			serverFuture.channel().closeFuture().sync();
			log.info("Netty End");

		} catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
			 if (serverFuture != null){
			 if (serverFuture.channel() != null && serverFuture.channel().isOpen()){
				 serverFuture.channel().close();
			 }
			 }
			 log.info("serverSend重啟");
			 serverSend();

			// 釋放線程池
			acceptGroup.shutdownGracefully();
			ioGroup.shutdownGracefully();

		}

	}

	public void connectSend(){
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(clientGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					socketChannel.pipeline().addLast(new LoggingHandler());
					socketChannel.pipeline().addLast(new IdleStateHandler(0, 30, 0, TimeUnit.SECONDS));
					socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1 << 10));
					socketChannel.pipeline().addLast(new StringEncoder());
					socketChannel.pipeline().addLast(new StringDecoder());
					socketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							ctx.writeAndFlush("hello\n");
						}
						@Override
						public void channelRead0(ChannelHandlerContext ctx, String msg) {
							log.info("client get response: {}", msg);
						}
						@Override
						public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
							if (evt instanceof IdleStateEvent) {
								IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
								if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
									// 30s未写数据就会发送心跳
									ctx.writeAndFlush("hi\n");
								}
							} else {
								super.userEventTriggered(ctx, evt);
							}
						}
						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
							cause.printStackTrace();
							ctx.close();
						}
					});
				}
			});
			
			log.info("ClientNetty Connet");
			clientRed = bootstrap.connect(IP, ReadPort).sync();
			clientRed.channel().closeFuture().sync();
			log.info("ClientNetty Stop Connet");
			
		} catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
			clientGroup.shutdownGracefully();
			 if (clientRed != null){
				 if (clientRed.channel() != null && clientRed.channel().isOpen()){
					 clientRed.channel().close();
				 }
				 }
				 log.info("connectSend重啟");
				 connectSend();
		}
	}

}

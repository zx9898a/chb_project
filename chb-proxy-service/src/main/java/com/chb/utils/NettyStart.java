package com.chb.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class NettyStart implements CommandLineRunner {

	@Autowired
	NettyControl nettyControl;

	@Override
	public void run(String... args) throws Exception {
		new Thread(() -> {
			try {
				nettyControl.serverSend();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		).start();

		new Thread(() -> {
			try {
				nettyControl.connectSend();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		).start();

	}

}

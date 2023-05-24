package com.chb.utils;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		NioStart server=new NioStart();
//		server.run();
		
		System.out.println(val(500000));
		
		System.out.println(val(400000,500000));
	}

	public static double val(double y) {
		
		double val = y/1.2;
		return val;
	}
	
	public static double val(double x,double y) {
		return y/x;
	}
	
}

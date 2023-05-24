package com.chb.utils;

@FunctionalInterface
public interface BasicBeanUtilsCallBack<S, T> {
	/**
	 * 定义默认回调方法
	 * 
	 * @param s
	 * @param t
	 */
	void callBack(S s, T t);
}

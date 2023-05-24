package com.chb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

public class BasicBeanUtils extends BeanUtils {

	/**
	 * 集合数据的拷贝
	 * 
	 * @param sources: 数据源类
	 * @param target: 目标类::new(eg: UserVO::new)
	 * @return
	 */
	public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
		return copyListProperties(sources, target, null);
	}

	/**
	 * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
	 * 
	 * @param sources: 数据源类
	 * @param target: 目标类::new(eg: UserVO::new)
	 * @param callBack: 回调函数
	 * @return
	 */
	public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target,
			BasicBeanUtilsCallBack<S, T> callBack) {
		List<T> list = new ArrayList<>(sources.size());
		for (S source : sources) {
			T t = target.get();
			copyProperties(source, t);
			list.add(t);
			if (callBack != null) {
				// 回调
				callBack.callBack(source, t);
			}
		}
		return list;
	}

	/**
	 * 带回 Map<S, T> 對應資料 轉換為 Object
	 * 
	 * @param sources: 数据源类
	 * @param target: 目标类::new(eg: UserVO::new)
	 * @param callBack: 回调函数
	 * @return
	 */
	public static <S, T> T copyMapProperties(Map<S, T> source, Supplier<T> target) {
		T t = target.get();
		for (Map.Entry<S, T> entry : source.entrySet()) {
			try {
				PropertyUtils.setProperty(t, String.valueOf(entry.getKey()), entry.getValue());
			} catch (Exception e) {
				// handle exception
			}
		}
		return t;
	}

}

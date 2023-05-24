package com.chb.service;

import java.util.List;

import com.chb.vo.order.OrderVO;


public interface OrderService {
	/** ActivityInfo */
	List<OrderVO> readOrderByAll() throws Exception;

	OrderVO readOrderById(OrderVO vo) throws Exception;

	void insertOrder(OrderVO vo) throws Exception;

	void updateOrder(OrderVO vo) throws Exception;
}

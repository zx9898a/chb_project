package com.chb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.OrderDAO;
import com.chb.entity.OrderEntity;
import com.chb.service.OrderService;
import com.chb.utils.BasicBeanUtils;
import com.chb.vo.order.OrderVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	public static final Gson GSON = new GsonBuilder().create();

	@Autowired
	OrderDAO orderDao;

	@Override
	public List<OrderVO> readOrderByAll() throws Exception {
		// TODO Auto-generated method stub
		List<OrderVO> result = new ArrayList<OrderVO>();
		List<OrderEntity> entity = orderDao.readAll();
		result = BasicBeanUtils.copyListProperties(entity, OrderVO::new);
		return result;
	}

	@Override
	public OrderVO readOrderById(OrderVO vo) throws Exception {
		// TODO Auto-generated method stub
		OrderVO result = new OrderVO();
		OrderEntity entity = orderDao.readById(vo.getOrdersId()).get();
		log.info("get value: " + entity.toString());
		BasicBeanUtils.copyProperties(result, entity);
		return result;
	}

	@Override
	public void insertOrder(OrderVO vo) throws Exception {
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(entity, vo);
		log.info("Insert Order: " + GSON.toJson(entity));
		orderDao.insertOrderInfo(entity);
	}

	@Override
	public void updateOrder(OrderVO vo) throws Exception {
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(entity, vo);
		log.info("Update Order: " + GSON.toJson(entity));
		orderDao.updateOrderInfo(entity);
	}

}

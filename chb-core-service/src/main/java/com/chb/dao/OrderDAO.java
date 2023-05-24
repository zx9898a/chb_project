package com.chb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.chb.entity.OrderEntity;
import com.chb.repository.OrderRepository;

@Repository
public class OrderDAO {

	private OrderRepository repository;

	public OrderDAO(OrderRepository repository) {
		super();
		this.repository = repository;
	}

	public List<OrderEntity> readAll() {
		return repository.findAll();
	}

	public Optional<OrderEntity> readById(String orderId) {
		return repository.findById(orderId);
	}

	public void insertOrderInfo(OrderEntity entity) {
		repository.save(entity);
	}

	public void updateOrderInfo(OrderEntity entity) {
		repository.update(entity);
	}

}

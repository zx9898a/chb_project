package com.chb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chb.entity.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>{
	
	@Modifying
	@Transactional
	@Query("update OrderEntity orders set orders.ordersContant =  :#{#entity.ordersContant} , "
			+ " orders.ordersDateStart =  :#{#entity.ordersDateStart} , " 
			+ " orders.ordersPrice =  :#{#entity.ordersPrice} " 
			+ " where orders.ordersId = :#{#entity.ordersId} ")
	void update(@Param("entity") OrderEntity entity);
	
}

package com.chb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5346023366857176318L;
	@Id
	@Column(name = "orders_id", nullable = false, unique = true)
	private String ordersId;
	@Column(name = "orders_employee_id")
	private String ordersEmployeeId;
	@Column(name = "orders_member_id")
	private String ordersMemberId;
	@Column(name = "orders_contant")
	private String ordersContant;
	@Column(name = "orders_price")
	private String ordersPrice;
	@Column(name = "orders_date_start")
	private String ordersDateStart;
	@Column(name = "orders_date_end")
	private String ordersDateEnd;
	@Column(name = "orders_status")
	private Integer ordersStatus;
}

package com.chb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productShipment")
@Data
public class ProductShipmentEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1558947121124585040L;
	@Id
	@Column(name = "productShipment_id", nullable = false, unique = true)
	private String productShipmentId;
	@Column(name = "product_id")
	private String productId;
	@Column(name = "shipment_count")
	private String shipmentCount;
	@Column(name = "shipment_date")
	private Date shipmentDate;
	@Column(name = "ec")
	private String ec;
	@Column(name = "productShipment_status")
	private Integer productShipmentStatus;
}

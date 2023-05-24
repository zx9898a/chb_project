package com.chb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5229955485954853797L;
	@Id
	@Column(name = "product_id", nullable = false, unique = true)
	private String productId;
	@Column(name = "productType")
	private String productType;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_price")
	private String productPrice;
	@Column(name = "product_status")
	private Integer productStatus;
	@Column(name = "product_total_count")
	private String productTotalCount;
}

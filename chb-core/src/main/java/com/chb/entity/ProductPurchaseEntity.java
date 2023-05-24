package com.chb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productPurchase")
@Data
public class ProductPurchaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6601919144633653049L;
	@Id
	@Column(name = "productPurchase_id", nullable = false, unique = true)
	private String productPurchaseId;
	@Column(name = "product_id")
	private String productId;
	@Column(name = "purchase_price")
	private String purchasePrice;
	@Column(name = "purchase_count")
	private String purchaseCount;
	@Column(name = "purchase_date")
	private Date purchaseDate;
	@Column(name = "invoice_date")
	private Date invoiceDate;
	@Column(name = "employees_id")
	private String employeesId;
	@Column(name = "ec")
	private String ec;
	@Column(name = "productPurchase_status")
	private Integer productPurchaseStatus;
}

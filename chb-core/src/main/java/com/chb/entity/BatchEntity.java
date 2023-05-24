package com.chb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BatchEntity {
	@Id
	private String productId;

	private String productPrice;

	private String productTotalCount;

	private String productPurchasePrice;

	private String productPurchaseCount;

	private Date productPurchaseDate;

	private String employeesName;
}

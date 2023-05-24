package com.chb.vo.product;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ProductPurchaseVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6511299380535253166L;

	private String productPurchaseId;
	private String productId;
	private String purchasePrice;
	private String purchaseCount;
	private Date purchaseDate;
	private Date invoiceDate;
	private String employeesId;
	private String ec;
	private Integer productPurchaseStatus;

}

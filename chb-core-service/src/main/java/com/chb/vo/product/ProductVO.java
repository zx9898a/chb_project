package com.chb.vo.product;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProductVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3113947599168977541L;
	private String productId;
	private String productType;
	private String productName;
	private String productPrice;
	private Integer productStatus;
	private String productTotalCount;
}

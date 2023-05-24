package com.chb.vo.order;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5570852698986215361L;

	private String ordersId;
	private String ordersEmployeeId;
	private String ordersMemberId;
	private String ordersContant;
	private String ordersPrice;
	private String ordersDateStart;
	private String ordersDateEnd;
	private Integer ordersStatus;

}

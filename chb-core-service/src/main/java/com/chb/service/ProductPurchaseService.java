package com.chb.service;

import java.util.List;

import com.chb.vo.product.ProductPurchaseVO;


public interface ProductPurchaseService {
	/** Product */
	List<ProductPurchaseVO> readProductPurchaseByAll() throws Exception;

	ProductPurchaseVO readProductPurchaseById(ProductPurchaseVO vo) throws Exception;

	void insertProductPurchase(ProductPurchaseVO vo) throws Exception;
}

package com.chb.service;

import java.util.List;

import com.chb.vo.product.ProductVO;


public interface ProductService {
	/** Product */
	List<ProductVO> readProductByAll() throws Exception;

	ProductVO readProductById(ProductVO vo) throws Exception;

	void insertProduct(ProductVO vo) throws Exception;

	void updateProduct(ProductVO vo) throws Exception;
}

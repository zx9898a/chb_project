package com.chb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.ProductPurchaseDAO;
import com.chb.entity.ProductPurchaseEntity;
import com.chb.service.ProductPurchaseService;
import com.chb.utils.BasicBeanUtils;
import com.chb.vo.product.ProductPurchaseVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductPurchaseServiceImpl implements ProductPurchaseService {

	public static final Gson GSON = new GsonBuilder().create();

	@Autowired
	ProductPurchaseDAO productPurchaseDAO;

	@Override
	public List<ProductPurchaseVO> readProductPurchaseByAll() throws Exception {
		// TODO Auto-generated method stub
		List<ProductPurchaseVO> reslut = new ArrayList<ProductPurchaseVO>();
		List<ProductPurchaseEntity> entity = productPurchaseDAO.readAll();
		reslut = BasicBeanUtils.copyListProperties(entity, ProductPurchaseVO::new);
		return reslut;
	}

	@Override
	public ProductPurchaseVO readProductPurchaseById(ProductPurchaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		ProductPurchaseVO reslut = new ProductPurchaseVO();
		ProductPurchaseEntity entity = productPurchaseDAO.readById(vo.getProductPurchaseId()).get();
		log.info("get value: " + entity.toString());
		BasicBeanUtils.copyProperties(reslut, entity);
		return reslut;
	}

	@Override
	public void insertProductPurchase(ProductPurchaseVO vo) throws Exception {
		// TODO Auto-generated method stub
		ProductPurchaseEntity entity = new ProductPurchaseEntity();
		BasicBeanUtils.copyProperties(entity, vo);
		productPurchaseDAO.insertProductPurchase(entity);
		log.info("DB Inesert Info: " + GSON.toJson(entity));
	}
}

package com.chb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.dao.ProductDAO;
import com.chb.entity.ProductEntity;
import com.chb.service.ProductService;
import com.chb.utils.BasicBeanUtils;
import com.chb.vo.product.ProductVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	public static final Gson GSON = new GsonBuilder().create();

	@Autowired
	ProductDAO productDao;

	@Override
	public List<ProductVO> readProductByAll() throws Exception {
		// TODO Auto-generated method stub
		List<ProductVO> result = new ArrayList<ProductVO>();
		List<ProductEntity> entityList = productDao.readAll();
		result = BasicBeanUtils.copyListProperties(entityList, ProductVO::new);
		return result;
	}

	@Override
	public ProductVO readProductById(ProductVO vo) throws Exception {
		// TODO Auto-generated method stub
		ProductVO result = new ProductVO();
		ProductEntity entity = productDao.readById(vo.getProductId()).get();
		log.info("get value: " + entity.toString());
		BasicBeanUtils.copyProperties(result, entity);
		return result;
	}

	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		// TODO Auto-generated method stub
		ProductEntity entity = new ProductEntity();
		BasicBeanUtils.copyProperties(entity, vo);
		productDao.insertProduct(entity);
		log.info("DB Inesert Info: " + GSON.toJson(entity));
	}

	@Override
	public void updateProduct(ProductVO vo) throws Exception {
		// TODO Auto-generated method stub
		ProductEntity entity = new ProductEntity();
		BasicBeanUtils.copyProperties(entity, vo);
		productDao.updateProduct(entity);
		log.info("Update product : " + GSON.toJson(entity));
	}
}

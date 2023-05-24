package com.chb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.chb.entity.ProductPurchaseEntity;
import com.chb.repository.ProductPurchaseRepository;

@Repository
public class ProductPurchaseDAO {

	private ProductPurchaseRepository productPurchaseRepository;

	public ProductPurchaseDAO(ProductPurchaseRepository productPurchaseRepository) {
		super();
		this.productPurchaseRepository = productPurchaseRepository;
	}

	public void insertProductPurchase(ProductPurchaseEntity entity) {
		productPurchaseRepository.save(entity);
	}

	public List<ProductPurchaseEntity> readAll() {
		return productPurchaseRepository.findAll();
	}

	public Optional<ProductPurchaseEntity> readById(String id) {
		return productPurchaseRepository.findById(id);
	}
}

package com.chb.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.chb.entity.ProductEntity;
import com.chb.repository.ProductRepository;

@Repository
public class ProductDAO {

	private ProductRepository productRepository;

	public ProductDAO(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public void insertProduct(ProductEntity entity) {
		productRepository.save(entity);
	}

	public List<ProductEntity> readAll() {
		return productRepository.findAll();
	}

	public Optional<ProductEntity> readById(String productId) {
		return productRepository.findById(productId);
	}

	public void updateProduct(ProductEntity entity) {
		productRepository.update(entity);
	}

}

package com.chb.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chb.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
	@Modifying
	@Transactional
	@Query("update ProductEntity product set product.productTotalCount =  :#{#entity.productTotalCount} , "
			+ " product.productPrice =  :#{#entity.productPrice} "
			+ " where product.productId = :#{#entity.productId} ")
	void update(@Param("entity") ProductEntity entity);

}

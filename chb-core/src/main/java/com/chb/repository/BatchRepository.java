package com.chb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chb.entity.BatchEntity;

public interface BatchRepository extends JpaRepository<BatchEntity, String> {
//	@Modifying
//	@Transactional
	@Query(value = " select pp.product_id as productId , p.product_price as productPrice , p.product_total_count as productTotalCount , pp.purchase_price as productPurchasePrice  , pp.purchase_count as productPurchaseCount , pp.purchase_date as productPurchaseDate , e.employees_name as employeesName "
			+ " from (( productPurchase pp " + " inner join product p on pp.product_id = p.product_id ) "
			+ " inner join employees e on pp.employees_id = e.employees_id ) "
			+ " where pp.product_id = :productId ", nativeQuery = true)
	List<BatchEntity> sendBatchDetail(@Param("productId") String productId);

}

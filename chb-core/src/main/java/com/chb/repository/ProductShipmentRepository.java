package com.chb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chb.entity.ProductShipmentEntity;

@Repository
public interface ProductShipmentRepository extends JpaRepository<ProductShipmentEntity, String> {

}

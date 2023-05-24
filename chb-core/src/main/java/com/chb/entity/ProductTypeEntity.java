package com.chb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "productType")
@Data
public class ProductTypeEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6007819881179973465L;
	@Id
	@Column(name = "productType_id", nullable = false, unique = true)
	private String productTypeId;
	@Column(name = "productType")
	private String productType;

}

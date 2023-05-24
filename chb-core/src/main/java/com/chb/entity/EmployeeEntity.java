package com.chb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 5844449704353251029L;

	@Id
	@Column(name = "employees_id", nullable = false, unique = true)
	private String employeesId;
	@Column(name = "employees_name")
	private String employeesName;
	@Column(name = "employees_tel")
	private String employeesTel;
	@Column(name = "employees_phone")
	private String employeesPhone;
	@Column(name = "employees_email")
	private String employeesEmail;
	@Column(name = "employees_adress")
	private String employeesAdress;
	@Column(name = "employees_department")
	private String employeesDepartment;
	@Column(name = "employees_psw")
	private String employeesPsw;
	@Column(name = "employees_level")
	private Integer employeesLevel;
	@Column(name = "employees_status")
	private Integer employeesStatus;
}

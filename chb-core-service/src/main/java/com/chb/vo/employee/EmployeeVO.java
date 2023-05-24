package com.chb.vo.employee;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4489226544258446615L;
	private String employeesId;
	private String employeesName;
	private String employeesTel;
	private String employeesPhone;
	private String employeesEmail;
	private String employeesAdress;
	private String employeesDepartment;
	private String employeesPsw;
	private Integer employeesLevel;
	private Integer employeesStatus;
}

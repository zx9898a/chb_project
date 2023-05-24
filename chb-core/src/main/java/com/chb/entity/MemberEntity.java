package com.chb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberEntity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -6233603313759219146L;

	@Id
	@Column(name = "mem_id", nullable = false, unique = true)
	private String memId;
	@Column(name = "mem_name")
	private String memName;
	@Column(name = "mem_tel")
	private String memTel;
	@Column(name = "mem_phone")
	private String memPhone;
	@Column(name = "mem_adress")
	private String memAdress;
	@Column(name = "mem_email")
	private String memEmail;
	@Column(name = "mem_birthday")
	private Date memBirthday;
	@Column(name = "mem_psw")
	private String memPsw;
	@Column(name = "mem_gender")
	private String memGender;
	@Column(name = "mem_img")
	private Byte memImg;
	@Column(name = "mem_level")
	private Integer memLevel;
	@Column(name = "mem_status")
	private Integer memStatus;

}

package com.chb.jwt.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2717067099721710139L;
	private String username;
	private String password;
}

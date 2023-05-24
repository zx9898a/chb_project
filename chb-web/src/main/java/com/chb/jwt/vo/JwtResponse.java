package com.chb.jwt.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 8801371900149739073L;
	private final String jwttoken;
}

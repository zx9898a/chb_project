package com.chb.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicOut<T> {
	private Integer code;
	@Default
	private String status = "s";
	private String message;
	private T data;
}

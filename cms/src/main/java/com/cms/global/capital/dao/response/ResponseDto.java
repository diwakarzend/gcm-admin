package com.cms.global.capital.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T, E> {

	private boolean status;
	private String message;
	private String id;
	private T data;
	private E errors;

	public ResponseDto(T data) {
		this.data = data;
	}

	public ResponseDto(boolean status) {
		this.status = status;
	}

	public ResponseDto(boolean status, String message) {
		this(status);
		this.message = message;
	}

	public ResponseDto(boolean status, String message, T data) {
		this(status, message);
		this.data = data;
	}
	
	public ResponseDto(boolean status, String message, T data , E errors) {
		this(status, message);
		this.data = data;
		this.errors = errors;
	}

	public ResponseDto(boolean status, String message, T data, String id) {
		this(status, message, data);
		this.id = id;
	}


	public static <T, E> ResponseDto<T, E> success(String message, T data) {
		return new ResponseDto<>(true, message, data);
	}
	
	public static <T, E> ResponseDto<T, E> failure(String message, T data) {
		return new ResponseDto<>(false, message, data);
	}
}
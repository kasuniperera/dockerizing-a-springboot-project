package com.ems.lms.dto;

import lombok.Data;

@Data
public class LmsApiResponse {

	private int statusCode;

	private String message;

	private Object data;

	public LmsApiResponse() {
		super();
	}

	public LmsApiResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public LmsApiResponse(int statusCode, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
}

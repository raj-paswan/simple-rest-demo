package com.simple.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;

@Data
@NoArgsConstructor
public class ErrorResponse {

	private static final Logger LOGGER = Logger.getLogger(ErrorResponse.class.getName());
	String error;
	String description;

	public ErrorResponse(String error, String description) {
		this.error = error;
		this.description = description;
	}
}

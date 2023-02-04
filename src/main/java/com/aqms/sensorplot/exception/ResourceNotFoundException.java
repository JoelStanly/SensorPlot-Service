package com.aqms.sensorplot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Resource Not Found Exception
@AllArgsConstructor
@Getter
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private String fieldValue;


}


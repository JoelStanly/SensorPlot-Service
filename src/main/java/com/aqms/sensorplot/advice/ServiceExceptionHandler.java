package com.aqms.sensorplot.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aqms.sensorplot.FileUtil;
import com.aqms.sensorplot.controller.SensorPlotController;
import com.aqms.sensorplot.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ServiceExceptionHandler {

	// Controller Advice Logging
	Logger log = FileUtil.getLogger(SensorPlotController.class);

	// Bad Request Handling when the data doesn't pass the validations
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidInput(MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		log.info("Bad Data given with the following errors:\n\n" + errorMap);

		return errorMap;

	}

	// Resource Not Found Exception Handling so that when data doesn't exists in the
	// DB
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getResourceName()+" of sensor :"+ex.getFieldValue()+" Not found");
		log.info("No Data found with the following errors:\n\n" + errorMap);
		return errorMap;
	}

	// Bad Request When a value cannot be contained in the required data type
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", ex.getMessage());
		log.info("Not Readable Exception with the following errors:\n\n" + errorMap);
		return errorMap;
	}

	// Generic Exception Handler
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(Exception.class)
//	public String genericExceptionHandler(Exception ex) {
//		log.info("Generic Exception");
//		return "Generic Exception: Request Failed";
//	}

}

package com.aqms.sensorplot;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Utility Class

public final class FileUtil {
	
	// Constants that can be changed based on business requirements here
	public static final int maxFloor = 3;
	public static final int maxRoom = 8;
	
	// Private Constructor so that Utility class cannot be instantiated
	private FileUtil() {
	}
	

	// Logger Instantiation Method
	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class clas) {
		Logger logger = LoggerFactory.getLogger(clas);
		return logger;
	}
	
	

}

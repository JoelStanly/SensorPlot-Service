package com.aqms.sensorplot.service;

import java.util.List;

import com.aqms.sensorplot.model.SensorPlot;
// Sensor Plot Service Interface

public interface SensorPlotService {
	
	SensorPlot saveSensorPlot(SensorPlot sensorPlot);
	List<SensorPlot> getSensorPlotAll();
	SensorPlot getSensorPlotById(String sensorid);
	
}

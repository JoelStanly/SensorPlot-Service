package com.aqms.sensorplot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aqms.sensorplot.FileUtil;
import com.aqms.sensorplot.model.SensorPlot;
import com.aqms.sensorplot.service.SensorPlotService;

import jakarta.validation.Valid;

// Rest Controller for Sensor Plot

@RestController
@RequestMapping("/SensorPlot")
public class SensorPlotController {
	
	// Controller Level Logging
	Logger log = FileUtil.getLogger(SensorPlotController.class);
	
	private SensorPlotService sensorPlotService;

	
	public SensorPlotController(SensorPlotService sensorPlotService) {
		super();
		this.sensorPlotService = sensorPlotService;
	}

	// Posting Sensor Plot Data
	@PostMapping("/post")
	@ResponseBody
	public SensorPlot saveSensorPlotControl(@RequestBody @Valid SensorPlot sensorPlot) {
		log.info("Sensor Details is being saved with data:\n" + sensorPlot);
		return sensorPlotService.saveSensorPlot(sensorPlot);
	}
	
	// Getting all the Data from The sensor Plot DB
	@GetMapping("/all")
	@ResponseBody
	public List<SensorPlot> getSensorPlotAllControl(){
		
		log.info("Getting all Sensor Details");
		return sensorPlotService.getSensorPlotAll();
	}
	
	// Getting a specific Data from the Sensor Plot DB with the ID of the given Sensor ID
	@GetMapping("/find/{sensorid}")
	@ResponseBody
	public SensorPlot getSensorPlotUsingIdControl(@PathVariable("sensorid") String sensorid) {
		log.info("Getting the sensor details of the sensor with id: "+sensorid);
		return sensorPlotService.getSensorPlotById(sensorid);
	}
}

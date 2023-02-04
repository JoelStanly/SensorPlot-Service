package com.aqms.sensorplot.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aqms.sensorplot.exception.ResourceNotFoundException;
import com.aqms.sensorplot.model.SensorPlot;
import com.aqms.sensorplot.repository.SensorPlotRepo;
import com.aqms.sensorplot.service.SensorPlotService;

// The implementation of the service interface
@Service
public class SensorPlotServiceImplement implements SensorPlotService {
	
	private SensorPlotRepo sensorPlotRepository;
	

	public SensorPlotServiceImplement(SensorPlotRepo sensorPlotRepository) {
		super();
		this.sensorPlotRepository = sensorPlotRepository;
	}

	// Save Data into the DB	
	@Override
	public SensorPlot saveSensorPlot(SensorPlot sensorPlot) {
		
		return sensorPlotRepository.save(sensorPlot);
		
	}

	// Get all Data from DB
	@Override
	public List<SensorPlot> getSensorPlotAll() {
		return sensorPlotRepository.findAll();
	}

	// Get data from DB for ID
	@Override
	public SensorPlot getSensorPlotById(String sensorid) {
		SensorPlot sensorPlot = sensorPlotRepository.findById(sensorid);
		if(sensorPlot == null) {
			throw new ResourceNotFoundException("SensorPlot","SensorID",sensorid);
		}
		return sensorPlot;
	}


}

package com.aqms.sensorplot.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.aqms.sensorplot.model.SensorPlot;

@Repository
public class SensorPlotRepo {

	@Autowired
	DynamoDBMapper mapper;

	public SensorPlot save(SensorPlot sensorPlot) {
		// TODO Auto-generated method stub
		mapper.save(sensorPlot);
		return sensorPlot;
	}

	public List<SensorPlot> findAll() {
		// TODO Auto-generated method stub
		return mapper.scan(SensorPlot.class, new DynamoDBScanExpression());
	}

	public SensorPlot findById(String sensorid) {
		return mapper.load(SensorPlot.class,sensorid);
	}
	
	

}

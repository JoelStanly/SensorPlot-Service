package com.aqms.sensorplot.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.aqms.sensorplot.exception.ResourceNotFoundException;
import com.aqms.sensorplot.model.SensorPlot;
import com.aqms.sensorplot.repository.SensorPlotRepo;
import com.aqms.sensorplot.service.SensorPlotService;

@SpringBootTest
public class SensorPlotServiceImplementationTest {

	@Mock
	private SensorPlotRepo sensorPlotRepository;

	@InjectMocks
	SensorPlotService sensorPlotService = new SensorPlotServiceImplement(sensorPlotRepository);

	// When the Find By Sensor ID works successfully 
	@DisplayName("FindByID Plot - Success")
	@Test
	void test_When_FindByID_Plot_Sucess() {
		//Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		when(sensorPlotRepository.findById(anyString())).thenReturn(sensorPlot);
		
		//Actual
		SensorPlot sensorPlotActual = sensorPlotService.getSensorPlotById("1L");
		//Verify
		verify(sensorPlotRepository,times(1)).findById(anyString());
		//Assert
		assertNotNull(sensorPlotActual);
		assertEquals(sensorPlot.getSensorId(), sensorPlotActual.getSensorId());
		
	}
	
	// When the Find By Sensor ID fails 
	@DisplayName("FindByID Plot - Failure")
	@Test
	void test_When_FindByID_Plot_Failure() {
		//Mocking
		when(sensorPlotRepository.findById(anyString())).thenReturn(null);
		
		//Assert
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,()->sensorPlotService.getSensorPlotById("1L"));
		assertEquals("SensorPlot", exception.getResourceName());
		assertEquals("SensorID",exception.getFieldName());
		assertEquals("1L",exception.getFieldValue());
		//Verify
		verify(sensorPlotRepository,times(1)).findById(anyString());
	}
	
	// When Save Plot Works Successfully
	@DisplayName("Save Plot - Success")
	@Test
	void test_When_SavePlot_Success() {
		//Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		when(sensorPlotRepository.save(any(SensorPlot.class))).thenReturn(sensorPlot);
		
		//Actual
		SensorPlot sensorPlotActual = sensorPlotService.saveSensorPlot(sensorPlot);
		
		//Verify
		verify(sensorPlotRepository,times(1)).save(any());
		
		//Assert
		assertNotNull(sensorPlotActual);
		assertEquals(sensorPlotActual.getRoom(), sensorPlot.getRoom());
		assertEquals(sensorPlotActual.getFloor(), sensorPlot.getFloor());
		assertEquals(sensorPlotActual.getSensorId(), sensorPlot.getSensorId());
		
	}
	
	// When Get All Sensor Plot Successfully
	@DisplayName("Get All Plot - Success")
	@Test
	void test_When_GetAllPlot_Success() {
		//Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		List<SensorPlot> sensorPlots = new ArrayList<>();
		sensorPlots.add(sensorPlot);
		when(sensorPlotRepository.findAll()).thenReturn(sensorPlots);
		//Actual
		List<SensorPlot> sensorPlotsActual = sensorPlotService.getSensorPlotAll();
		//Verify
		verify(sensorPlotRepository,times(1)).findAll();
		//Assert
		
		assertNotNull(sensorPlotsActual);
		assertEquals(sensorPlotsActual, sensorPlots);
	}
	
	
	// Mock Sensor Plot
	private SensorPlot getMockSensorPlot() {
		return SensorPlot.builder().floor(1).room(1).sensorId("1l").build();
	}
	
}

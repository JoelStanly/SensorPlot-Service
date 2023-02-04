package com.aqms.sensorplot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.aqms.sensorplot.model.SensorPlot;
import com.aqms.sensorplot.service.SensorPlotService;

@WebMvcTest(SensorPlotController.class)
public class SensorPlotControllerTest {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SensorPlotService sensorPlotService;

	// Post Sensor Plot Works Successfully
	@DisplayName("Post SensorPlot")
	@Test
	public void test_Post_Plot() throws Exception {
		// Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		when(sensorPlotService.saveSensorPlot(any())).thenReturn(sensorPlot);

		// Actual
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/SensorPlot/post")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(sensorPlot)))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		SensorPlot sensorPlotResult = objectMapper.readValue(resultContent, SensorPlot.class);

		// Assert
		assertNotNull(sensorPlotResult);
		assertEquals(sensorPlotResult.getRoom(), sensorPlot.getRoom());
		assertEquals(sensorPlotResult.getFloor(), sensorPlot.getFloor());

		// Verify
		verify(sensorPlotService, times(1)).saveSensorPlot(any());

	}

	// Find By Sensor Plot by ID works successfully
	@DisplayName("Find SensorPlot By ID")
	@Test
	public void test_FindByID_Plot() throws Exception {
		// Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		when(sensorPlotService.getSensorPlotById(anyString())).thenReturn(sensorPlot);
		// Actual
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/SensorPlot/find/{sensorid}", "1L")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

		String resultContent = result.getResponse().getContentAsString();
		SensorPlot sensorPlotResult = objectMapper.readValue(resultContent, SensorPlot.class);

		// Assert
		assertNotNull(sensorPlotResult);
		assertEquals(sensorPlotResult.getRoom(), sensorPlot.getRoom());
		assertEquals(sensorPlotResult.getFloor(), sensorPlot.getFloor());
		assertEquals(sensorPlotResult.getSensorId(), sensorPlot.getSensorId());

		// Verify
		verify(sensorPlotService, times(1)).getSensorPlotById(anyString());

	}

	// Find All works successfully
	@DisplayName("Find All SensorPlots")
	@Test
	public void test_FindAll_Plot() throws Exception {
		// Mocking
		SensorPlot sensorPlot = getMockSensorPlot();
		List<SensorPlot> sensorPlots = new ArrayList<>();
		sensorPlots.add(sensorPlot);
		when(sensorPlotService.getSensorPlotAll()).thenReturn(sensorPlots);

		// Actual
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/SensorPlot/all")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		SensorPlot[] sensorPlotResult = objectMapper.readValue(resultContent, SensorPlot[].class);

		// Assert
		assertNotNull(sensorPlotResult);
		assertEquals(sensorPlotResult.length, 1);
		assertEquals(sensorPlotResult[0].getRoom(), sensorPlot.getRoom());
		assertEquals(sensorPlotResult[0].getFloor(), sensorPlot.getFloor());
		assertEquals(sensorPlotResult[0].getSensorId(), sensorPlot.getSensorId());

	}



	

	// Mock Sensor Plot
	private SensorPlot getMockSensorPlot() {
		return SensorPlot.builder().floor(1).room(1).sensorId("1L").build();
	}

}

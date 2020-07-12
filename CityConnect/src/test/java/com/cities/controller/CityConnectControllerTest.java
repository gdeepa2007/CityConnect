package com.cities.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cities.service.CityConnectService;

@WebMvcTest
class CityConnectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CityConnectService service;

	@Test
	@DisplayName("Controller test")
	void testCheckCityConnection() throws Exception {
		when(service.checkCityConnection("New York", "New Jersey")).thenReturn(true);
		mockMvc.perform(get("/connected?origin=New York&destination=New Jersey"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("true")));
	}

}

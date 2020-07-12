package com.cities.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class CityConnectControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCheckCityConnection() throws Exception {
		mockMvc.perform(get("/connected?origin=New York&destination=New Jersey"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("true")));
	}

}

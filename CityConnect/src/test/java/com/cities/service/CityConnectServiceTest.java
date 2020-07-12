package com.cities.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class CityConnectServiceTest {
	
	private CityConnectService service;
	private CityConnectService serviceMock;
	
	Map<String, List<String>> map;
	
	@BeforeAll
	void init () {
		map = new HashMap<String, List<String>>();
		map.put("NEW YORK", Arrays.asList("BOSTON"));
		map.put("NEWARK", Arrays.asList("PHILADELPHIA", "BOSTON"));
		map.put("PHILADELPHIA", Arrays.asList("NEWARK"));
		map.put("BOSTON", Arrays.asList("NEW YORK", "NEWARK"));
		
		service = new CityConnectService();
		
		serviceMock = Mockito.spy(service);
		Mockito.doReturn(map).when(serviceMock).getAllCities();
	}

	@Test
	@DisplayName ("Connected cities")
	void testCheckCityConnectionForConnectedCities() {
		assertAll(
				() -> assertTrue(serviceMock.checkCityConnection("New York", "Boston")),
				() -> assertTrue(serviceMock.checkCityConnection("new york", "boston"))
				);
		
	}
	
	@Test
	@DisplayName ("Same origin and destination")
	void testCheckCityConnectionForSameOrigAndDest() {
		assertTrue(serviceMock.checkCityConnection("New York", "new york"));
	}
	
	@Test
	@DisplayName ("Not connected cities")
	void testCheckCityConnectionForNotConnectedCities() {
		assertFalse(serviceMock.checkCityConnection("New York", "Newark"));
	}
	
	@Test
	@DisplayName ("Cities not in file")
	void testCheckCityConnectionForSourceCityNotInFile() {
		assertAll(
				() -> assertFalse(serviceMock.checkCityConnection("NewYork", "Boston")),
				() -> assertFalse(serviceMock.checkCityConnection("Boston", "Virginia")),
				() -> assertFalse(serviceMock.checkCityConnection("NewYork", "Virginia"))
				);
		
	}
	
	@Test
	@DisplayName ("Null input")
	void testCheckCityConnectionForNullInput() {
		assertAll (
				() -> assertFalse(serviceMock.checkCityConnection(null, null)),
				() -> assertFalse(serviceMock.checkCityConnection(null, "Boston")),
				() -> assertFalse(serviceMock.checkCityConnection("Boston", null))
				);
		
	}
	
	@Test
	@DisplayName ("Blank input")
	void testCheckCityConnectionForBlankInput() {
		assertAll(
				() -> assertFalse(serviceMock.checkCityConnection("", "  ")),
				() -> assertFalse(serviceMock.checkCityConnection(" ", "Boston")),
				() -> assertFalse(serviceMock.checkCityConnection("Boston", ""))
				);
	}

}

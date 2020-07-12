package com.cities.util;

import java.util.Collection;
import java.util.Map;

public class CityConnectUtil {

	/**
	 * Utility method to check if string is null or empty
	 * @param str
	 * @return true if the string is null or empty, else false
	 */
	public static boolean isNullorEmpty (final String str) {
		return ((null == str) || (str.trim().isEmpty()));
	}
	
		
	/**
	 * Utility method to check if Collection is null or empty
	 * @param c
	 * @return true if the collection is null or empty
	 */
	public static boolean isNullOrEmpty(final Collection<?> c) {
	    return c == null || c.isEmpty();
	}

	
	/**
	 * Utility method to check if map is null or empty
	 * @param m
	 * @return true if the map is null or empty
	 */
	public static boolean isNullOrEmpty(final Map<?, ?> map) {
	    return map == null || map.isEmpty();
	}
}

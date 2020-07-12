package com.cities.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class RequestFilter implements Filter {
    
	/**
	 * Generated a random uuid to be added in the log files.
	 * The key will be used in logging.pattern in application.properties
	 */
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
		try {
            String uuid = UUID.randomUUID().toString();
            MDC.put("uuid", uuid);
            chain.doFilter(request, response);
        
		} finally {
           MDC.clear();
        }
    }

	
}
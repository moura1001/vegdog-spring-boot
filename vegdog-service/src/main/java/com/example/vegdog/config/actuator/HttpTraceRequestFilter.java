package com.example.vegdog.config.actuator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("admin")
public class HttpTraceRequestFilter extends HttpTraceFilter {

	public HttpTraceRequestFilter(HttpTraceRepository repository, HttpExchangeTracer tracer) {
		super(repository, tracer);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().contains("actuator");
	}

}

package com.example.vegdog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.vegdog.model.bean.VegDogConfigProperties;
import com.example.vegdog.model.dto.OfertaDTO;

@Controller
@Profile("delivery")
public class DeliveryController {
	
	@Autowired
	private VegDogConfigProperties properties;
	
	@GetMapping("/")
	public String index() {
		return "delivery/index";
	}
	
	@GetMapping("/oferta")
	@ResponseBody
	public OfertaDTO oferta(HttpServletRequest request) {
		return new OfertaDTO(properties.getOferta(),
				request.getServerName()+":"+request.getServerPort(),
				properties.getServerInfo());
	}
	
	@GetMapping("/servidor")
	@ResponseBody
	public String servidor(HttpServletRequest request) {
		return request.getServerName()+":"+request.getServerPort();
	}
	
}

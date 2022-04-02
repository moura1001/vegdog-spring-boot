package com.example.vegdog.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Profile("delivery")
public class DeliveryController {
	
	@GetMapping("/")
	public String index() {
		return "delivery/index";
	}
}

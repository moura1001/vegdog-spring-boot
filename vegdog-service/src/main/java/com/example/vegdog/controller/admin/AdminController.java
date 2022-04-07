package com.example.vegdog.controller.admin;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Profile("admin")
public class AdminController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/ambiente")
	public String ambiente() {
		return "ambiente";
	}
	
	@GetMapping("/delivery")
	public String delivery() {
		return "delivery/index";
	}
}

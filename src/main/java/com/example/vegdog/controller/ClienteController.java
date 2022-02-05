package com.example.vegdog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.vegdog.model.entity.Cliente;
import com.example.vegdog.model.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Cliente> clientes = clienteRepository.findAll();
		return new ModelAndView("cliente/list", "clientes", clientes);
	}
	
	@GetMapping("/{id}")
	public ModelAndView view(@PathVariable("id") Cliente cliente) {
		return new ModelAndView("cliente/view", "cliente", cliente);
	}
}

package com.example.vegdog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Cliente cliente) {
		return "cliente/form";
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@Valid Cliente cliente, BindingResult result,
						RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			cliente = clienteRepository.save(cliente);
			redirect.addFlashAttribute("globalMessage", "Cliente cadastrado com sucesso");
			return new ModelAndView("redirect:/clientes/{cliente.id}",
					"cliente.id", cliente.getId());
		}
		return new ModelAndView("cliente/form", "formErrors", result.getAllErrors());
	}
}

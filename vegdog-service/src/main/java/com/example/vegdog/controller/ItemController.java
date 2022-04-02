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

import com.example.vegdog.model.entity.Item;
import com.example.vegdog.model.repository.ItemRepository;

@Controller
@RequestMapping("/itens")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Item> itens = itemRepository.findAll();
		return new ModelAndView("item/list", "itens", itens);
	}
	
	@GetMapping("/{id}")
	public ModelAndView view(@PathVariable("id") Item item) {
		return new ModelAndView("item/view", "item", item);
	}
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Item item) {
		return "item/form";
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@Valid Item item, BindingResult result,
						RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			item = itemRepository.save(item);
			redirect.addFlashAttribute("globalMessage", "Item cadastrado com sucesso");
			return new ModelAndView("redirect:/itens/{item.id}",
					"item.id", item.getId());
		}
		return new ModelAndView("item/form", "formErrors", result.getAllErrors());
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView updateForm(@PathVariable("id") Item item) {
		return new ModelAndView("item/form", "item", item);
	}
	
	@GetMapping("/remover/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirect) {
		itemRepository.deleteById(id);
		redirect.addFlashAttribute("globalMessage", "Item removido com sucesso");
		return "redirect:/itens/";
	}
}

package com.example.vegdog.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
import com.example.vegdog.model.entity.Item;
import com.example.vegdog.model.entity.Pedido;
import com.example.vegdog.model.repository.ClienteRepository;
import com.example.vegdog.model.repository.ItemRepository;
import com.example.vegdog.model.repository.PedidoRepository;

@Controller
@RequestMapping("/admin/pedidos")
@Profile("admin")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Pedido> pedidos = pedidoRepository.findAll();
		return new ModelAndView("pedido/list", "pedidos", pedidos);
	}
	
	@GetMapping("/{id}")
	public ModelAndView view(@PathVariable("id") Pedido pedido) {
		return new ModelAndView("pedido/view", "pedido", pedido);
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Pedido pedido) {
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("clientesCadastrados", clienteRepository.findAll());
		modelo.put("itensCadastrados", itemRepository.findAll());
		return new ModelAndView("pedido/form", modelo);
	}
	
	@PostMapping(params = "form")
	public ModelAndView create(@Valid Pedido pedido, BindingResult result,
						RedirectAttributes redirect) {
		if(!result.hasErrors()) {
			if(pedido.getId() != null) {
				Pedido pedidoAlterado = pedidoRepository.findById(pedido.getId()).get();
				
				Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).get();
				
				pedidoAlterado.setItens(pedido.getItens());				
				double precoTotal = 0;
				for(Item item : pedido.getItens()) {
					precoTotal += item.getPreco();
				}
				pedidoAlterado.setPrecoTotal(precoTotal);
				
				cliente.removePedido(pedido);
				cliente.addPedido(pedidoAlterado);
				clienteRepository.save(cliente);
				
			} else {
				Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).get();
				
				double precoTotal = 0;
				for(Item item : pedido.getItens()) {
					precoTotal += item.getPreco();
				}
				pedido.setData(new Date(System.currentTimeMillis()));
				pedido.setPrecoTotal(precoTotal);
				pedido = pedidoRepository.save(pedido);
				
				cliente.addPedido(pedido);
				clienteRepository.save(cliente);
			}
			redirect.addFlashAttribute("globalMessage", "Pedido cadastrado com sucesso");
			return new ModelAndView("redirect:/admin/pedidos/{pedido.id}",
					"pedido.id", pedido.getId());
		}
		return new ModelAndView("pedido/form", "formErrors", result.getAllErrors());
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView updateForm(@PathVariable("id") Pedido pedido) {
		Map<String, Object> modelo = new HashMap<String, Object>();
		modelo.put("clientesCadastrados", clienteRepository.findAll());
		modelo.put("itensCadastrados", itemRepository.findAll());
		modelo.put("pedido", pedido);
		return new ModelAndView("pedido/form", modelo);
	}
	
	@GetMapping("/remover/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirect) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedido.getCliente().removePedido(pedido);
		pedido.setCliente(null);
		pedidoRepository.delete(pedido);
		redirect.addFlashAttribute("globalMessage", "Pedido removido com sucesso");
		return "redirect:/admin/pedidos/";
	}
}

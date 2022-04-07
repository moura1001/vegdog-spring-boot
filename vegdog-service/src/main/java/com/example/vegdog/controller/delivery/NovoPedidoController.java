package com.example.vegdog.controller.delivery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.vegdog.model.dto.PedidoDTO;
import com.example.vegdog.model.dto.RespostaPedidoDTO;
import com.example.vegdog.model.entity.Cliente;
import com.example.vegdog.model.entity.Item;
import com.example.vegdog.model.entity.Pedido;
import com.example.vegdog.model.repository.ClienteRepository;
import com.example.vegdog.model.repository.ItemRepository;

@RestController
public class NovoPedidoController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@PostMapping("/delivery/rest/pedido/novo")
	public RespostaPedidoDTO novo(@RequestBody PedidoDTO pedido) {
		RespostaPedidoDTO resposta = new RespostaPedidoDTO();
		
		try {
			
			Optional<Cliente> c = clienteRepository.findByNome(pedido.getClienteNome());
						
			if(c.isEmpty()) {
				throw new Exception("Usuário não cadastrado");
			}
			
			Cliente cliente = c.get();
			
			Pedido novoPedido = new Pedido();
			
			Double precoTotal = 0d;
			List<Item> itens = new ArrayList<Item>();
			for(Long itemId: pedido.getItensId()) {
				Item item = itemRepository.findById(itemId).get();
				itens.add(item);
				
				precoTotal += item.getPreco();
			}
			
			novoPedido.setCliente(cliente);
			novoPedido.setItens(itens);
			novoPedido.setData(new Date());
			novoPedido.setPrecoTotal(precoTotal);
			
			cliente.getPedidos().add(novoPedido);
			clienteRepository.saveAndFlush(cliente);
			
			List<Pedido> pedidos = cliente.getPedidos();
			Long ultimoPedido = pedidos.get(pedidos.size() - 1).getId();
			
			resposta.setPedido(ultimoPedido);
			resposta.setPrecoTotal(precoTotal);
			resposta.setMensagem("Pedido efetuado com sucesso");
			
		} catch(Exception e) {
			resposta.setMensagem("Erro: " + e.getMessage());
		}
		
		return resposta;
	}
	
}

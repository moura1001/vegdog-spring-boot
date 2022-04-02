package com.example.vegdog.config.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.vegdog.model.entity.Cliente;
import com.example.vegdog.model.entity.Item;
import com.example.vegdog.model.entity.Pedido;
import com.example.vegdog.model.repository.ClienteRepository;

@Component
@Profile("dev")
class DataLoader implements ApplicationRunner {

	private static final long ID_CLIENTE_JOAO = 10l;
	private static final long ID_CLIENTE_MARIA = 20l;
	
	private static final long ID_ITEM1 = 100l;
	private static final long ID_ITEM2 = 200l;
	private static final long ID_ITEM3 = 300l;
	
	private static final long ID_PEDIDO1 = 1000l;
	private static final long ID_PEDIDO2 = 2000l;
	private static final long ID_PEDIDO3 = 3000l;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println(">>> Iniciando carga de dados...");
		
		Cliente joao = new Cliente(ID_CLIENTE_JOAO, "João Maria", "Lugarnenhum");
		
		Item dog1 = new Item(ID_ITEM1, "Green Dog tradicional", 8d);
		Item dog2 = new Item(ID_ITEM2, "Green Dog tradicional picante", 16d);
		Item dog3 = new Item(ID_ITEM3, "Green Dog max salada", 32d);
		
		List<Item> listaPedidoJoao1 = new ArrayList<Item>();
		listaPedidoJoao1.add(dog1);
		listaPedidoJoao1.add(dog2);
		
		List<Pedido> pedidosJoao = new ArrayList<Pedido>();
		pedidosJoao.add(new Pedido(ID_PEDIDO1, joao, listaPedidoJoao1, dog1.getPreco() + dog2.getPreco()));
		joao.setPedidos(pedidosJoao);
		
		System.out.println(">>> Pedido - João: " + pedidosJoao.get(0));
		
		clienteRepository.saveAndFlush(joao);
		System.out.println(">>> Cliente 1 gravado: " + joao);
		
		Cliente maria = new Cliente(ID_CLIENTE_MARIA, "Maria João", "Algumlugar");
		
		List<Item> listaPedidoMaria1 = new ArrayList<Item>();
		listaPedidoMaria1.add(dog3);
		
		List<Pedido> pedidosMaria = new ArrayList<Pedido>();
		pedidosMaria.add(new Pedido(ID_PEDIDO2, maria, listaPedidoMaria1, dog3.getPreco()));
		
		System.out.println(">>> Pedido 1 - Maria: " + pedidosMaria.get(0));
		
		List<Item> listaPedidoMaria2 = new ArrayList<Item>();
		listaPedidoMaria2.add(dog2);
		
		pedidosMaria.add(new Pedido(ID_PEDIDO3, maria, listaPedidoMaria2, dog2.getPreco()));
		maria.setPedidos(pedidosMaria);
		
		System.out.println(">>> Pedido 2 - Maria: " + pedidosMaria.get(1));
		
		clienteRepository.saveAndFlush(maria);
		System.out.println(">>> Cliente 2 gravado: " + maria);
	}

}

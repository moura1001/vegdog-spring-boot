package com.example.vegdog;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"dev", "admin"})
class VegDogApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void deveriaConterOServicoRESTParaClientes() {
		try {
			URI uri = new URI("/api");
			
			mockMvc
			.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("clientes")));
		} catch (Exception e) {
			fail("Test failed: " + e.getMessage());
		}
	}
	
	@Test
	void oPrecoDoPrimeiroItemCadastradoDeveriaSer8Reais() {
		try {
			URI uri = new URI("/api/itens/1");
			
			mockMvc
			.perform(get(uri))
			.andExpect(status().isOk())
			.andExpect(jsonPath("preco", equalTo(8d)));
		} catch (Exception e) {
			fail("Test failed: " + e.getMessage());
		}
	}
	
	@Test
	void deveriaCadastrarUmNovoPedido() {
		try {
			URI uri = new URI("/rest/pedido/novo");
			String json = "{\"clienteNome\":\"João Maria\",\"itensId\":[1,2,3]}";
			
			mockMvc
			.perform(post(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("pedido", greaterThan(3)))
			.andExpect(jsonPath("precoTotal", equalTo(56d)))
			.andExpect(jsonPath("mensagem", equalTo("Pedido efetuado com sucesso")));
		} catch (Exception e) {
			fail("Test failed: " + e.getMessage());
		}
	}

}

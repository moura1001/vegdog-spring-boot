package com.example.vegdog.model.repository;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.vegdog.model.entity.Cliente;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository repository;

	@Test
	void deveriaBuscarTodosOsClientesCadastrados() {
		List<Cliente> clientes = repository.findAll();
		assertThat(clientes.size(), greaterThan(1));
	}
	
	@Test
	void deveriaBuscarAClienteMaria() {
		Optional<Cliente> c = repository.findByNome("Maria");
		assertTrue(c.isEmpty());
		
		c = repository.findByNome("Maria João");
		assertTrue(c.isPresent());
		
		Cliente cliente = c.get();
		
		assertThat(cliente.getNome(), equalTo("Maria João"));
		assertThat(cliente.getEndereco(), equalTo("Algumlugar"));
	}

}

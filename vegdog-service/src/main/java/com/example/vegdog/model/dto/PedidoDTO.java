package com.example.vegdog.model.dto;

import java.util.List;

public class PedidoDTO {
	
	private String clienteNome;
	private List<Long> itensId;
	
	public PedidoDTO() {}
	
	public PedidoDTO(String clienteNome, List<Long> itensId) {
		this.clienteNome = clienteNome;
		this.itensId = itensId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public List<Long> getItensId() {
		return itensId;
	}

	public void setItensId(List<Long> itensId) {
		this.itensId = itensId;
	}
	
}

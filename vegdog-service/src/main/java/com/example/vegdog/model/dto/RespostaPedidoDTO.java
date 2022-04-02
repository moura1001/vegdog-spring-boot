package com.example.vegdog.model.dto;

public class RespostaPedidoDTO {
	
	private Long pedido;
	private Double precoTotal;
	private String mensagem;
	
	public RespostaPedidoDTO() {}
	
	public RespostaPedidoDTO(Long pedido, Double precoTotal, String mensagem) {
		this.pedido = pedido;
		this.precoTotal = precoTotal;
		this.mensagem = mensagem;
	}

	public Long getPedido() {
		return pedido;
	}
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

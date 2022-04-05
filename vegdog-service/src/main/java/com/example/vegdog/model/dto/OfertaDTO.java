package com.example.vegdog.model.dto;

public class OfertaDTO {
	
	private String oferta;
	private String servidor;
	private boolean serverInfo;

	public OfertaDTO(String oferta, String servidor, boolean serverInfo) {
		this.oferta = oferta;
		this.servidor = servidor;
		this.serverInfo = serverInfo;
	}

	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

	public boolean getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(boolean serverInfo) {
		this.serverInfo = serverInfo;
	}

}

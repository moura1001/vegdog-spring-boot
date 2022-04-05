package com.example.vegdog.model.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vegdog")
@RefreshScope
public class VegDogConfigProperties {
	
	private String oferta;
	
	private boolean serverInfo;
	
	public String getOferta() {
		return oferta;
	}
	
	public void setOferta(String oferta) {
		this.oferta = oferta;
	}
	
	public boolean getServerInfo() {
		return serverInfo;
	}
	
	public void setServerInfo(boolean serverInfo) {
		this.serverInfo = serverInfo;
	}

}

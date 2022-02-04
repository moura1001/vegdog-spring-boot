package com.example.vegdog.model.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = true)
	private Cliente cliente;
	
	@ManyToMany
	@Cascade(CascadeType.MERGE)
	private List<Item> itens;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date data;
	
	@Min(Long.MIN_VALUE)
	private Double precoTotal;
	
	public Pedido() {}

	public Pedido(long id, Cliente cliente, List<Item> itens, double precoTotal) {
		this.id = id;
		this.cliente = cliente;
		this.itens = itens;
		this.precoTotal = precoTotal;
		this.data = getCurrentDateDayMonthYear();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	private Date getCurrentDateDayMonthYear() {
		String pattern = "dd-MM-yyyy";
		DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
		
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

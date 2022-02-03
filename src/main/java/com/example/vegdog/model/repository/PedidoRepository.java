package com.example.vegdog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vegdog.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

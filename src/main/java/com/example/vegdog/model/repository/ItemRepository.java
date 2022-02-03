package com.example.vegdog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vegdog.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}

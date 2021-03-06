package com.example.vegdog.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.vegdog.model.entity.Item;

@RepositoryRestResource(collectionResourceRel = "itens", path="itens")
public interface ItemRepository extends JpaRepository<Item, Long> {

}

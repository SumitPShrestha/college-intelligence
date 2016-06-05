package com.cms.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}

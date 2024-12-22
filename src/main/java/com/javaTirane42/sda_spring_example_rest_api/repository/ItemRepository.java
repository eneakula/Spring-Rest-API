package com.javaTirane42.sda_spring_example_rest_api.repository;

import com.javaTirane42.sda_spring_example_rest_api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}

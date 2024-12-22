package com.javaTirane42.sda_spring_example_rest_api.service;

import com.javaTirane42.sda_spring_example_rest_api.entity.Item;
import com.javaTirane42.sda_spring_example_rest_api.model.ItemDTO;
import com.javaTirane42.sda_spring_example_rest_api.repository.ItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ItemService {

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(ItemDTO itemDTO) {
        Item item = new Item();

        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setPrice(itemDTO.getPrice());
        item.setCategory(itemDTO.getCategory());

        return itemRepository.save(item);
    }

    public Optional<Item> updateItem(Item item) {

        Optional<Item> existingItem = itemRepository.findById(item.getId());

        if (existingItem.isEmpty()) {
            return Optional.empty();
        }

        Item updatedItem = existingItem.get();
        updatedItem.setName(item.getName());
        updatedItem.setDescription(item.getDescription());
        updatedItem.setPrice(item.getPrice());
        updatedItem.setCategory(item.getCategory());

        return Optional.of(itemRepository.save(updatedItem));
    }

    public boolean deleteItem(Long id) {

        Optional<Item> existingItem = itemRepository.findById(id);

        if (existingItem.isEmpty()) {
            return false;
        }

        itemRepository.deleteById(id);
        return true;
    }

}

package com.javaTirane42.sda_spring_example_rest_api.controller;

import com.javaTirane42.sda_spring_example_rest_api.entity.Item;
import com.javaTirane42.sda_spring_example_rest_api.model.ItemDTO;
import com.javaTirane42.sda_spring_example_rest_api.service.ItemService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestController
@RequestMapping("/items")
public class ItemController {

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    private final ItemService itemService;

    @GetMapping("all")
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("create")
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO itemDTO) {
        return ResponseEntity.ok(itemService.createItem(itemDTO));

    }

    @PutMapping("update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        return itemService.updateItem(item)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
/*        return itemService.deleteItem(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();*/

        if (itemService.deleteItem(id)) {
            return ResponseEntity.ok("Item deleted");
        } else {
            return ResponseEntity.status(BAD_REQUEST).body("Item not deleted");
        }
    }
}



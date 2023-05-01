package com.denizkpln.inventoryservice.controller;


import com.denizkpln.inventoryservice.dto.InventoryResponse;
import com.denizkpln.inventoryservice.model.Inventory;
import com.denizkpln.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory/iphone-13,iphone13-red

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Inventory>> getAll(){
        List<Inventory> inventoryList= inventoryService.getAll();
        return new ResponseEntity<>(inventoryList,HttpStatus.OK);
    }
}


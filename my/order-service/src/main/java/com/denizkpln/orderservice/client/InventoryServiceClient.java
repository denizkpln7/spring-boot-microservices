package com.denizkpln.orderservice.client;


import com.denizkpln.orderservice.dto.Inventory;
import com.denizkpln.orderservice.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "inventory-service" , path = "/api/inventory")
public interface InventoryServiceClient {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);

    @GetMapping("/getall")
    ResponseEntity<List<Inventory>> getAll();
}

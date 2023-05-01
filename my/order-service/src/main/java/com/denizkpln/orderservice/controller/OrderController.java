package com.denizkpln.orderservice.controller;


import com.denizkpln.orderservice.config.JsonKafkaProducer;
import com.denizkpln.orderservice.dto.OrderRequest;
import com.denizkpln.orderservice.model.Order;
import com.denizkpln.orderservice.model.OrderLineItems;
import com.denizkpln.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final JsonKafkaProducer jsonKafkaProducer;

//    @Value("${spring.application.size}")
//    private String count;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Order>> getAll(){
      List<Order> orderList=  orderService.getall();
      return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @PostMapping("/kafkaproducer")
    public ResponseEntity<String> publish(){
        OrderLineItems order=new OrderLineItems();
        order.setId(1L);
        order.setQuantity(5);
        order.setPrice(BigDecimal.valueOf(100));
        jsonKafkaProducer.sendMessage(order);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }

//    @GetMapping("/getvalue")
//    public String getValue(){
//        return count;
//    }
}

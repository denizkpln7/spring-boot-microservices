package com.denizkpln.orderservice.service;


import com.denizkpln.orderservice.client.InventoryServiceClient;
import com.denizkpln.orderservice.dto.Inventory;
import com.denizkpln.orderservice.dto.OrderLineItemsDto;
import com.denizkpln.orderservice.dto.OrderRequest;
import com.denizkpln.orderservice.model.Order;
import com.denizkpln.orderservice.model.OrderLineItems;
import com.denizkpln.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;


    public OrderService(OrderRepository orderRepository, InventoryServiceClient inventoryServiceClient) {
        this.orderRepository = orderRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // Call Inventory Service, and place order if product is in
        // stock
//        InventoryResponse[] inventoryResponsArray = webClient.get()
//                .uri("http://localhost:8082/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();

//        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
//                .allMatch(InventoryResponse::isInStock);

//        if(allProductsInStock){
//            orderRepository.save(order);
//        } else {
//            throw new IllegalArgumentException("Product is not in stock, please try again later");
//        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

    public List<Order> getall() {
        List<Inventory> inventoryList=inventoryServiceClient.getAll().getBody();
        return orderRepository.findAll();
    }
}

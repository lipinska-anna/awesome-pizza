package com.demo.awesomepizza.rest;

import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.rest.dto.OrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderControllerI {
    ResponseEntity<OrderDto> createOrder(OrderDto order);
    ResponseEntity<OrderDto> updateOrder(OrderDto order);
    ResponseEntity<OrderDto> updateOrderStatus(String id, OrderStatusEnum status) throws Exception;
    ResponseEntity<Void> deleteOrder(String id);
    ResponseEntity<OrderDto> getOrder(String id) throws Exception;
    ResponseEntity<List<OrderDto>> getOrderQueue() throws Exception;
}

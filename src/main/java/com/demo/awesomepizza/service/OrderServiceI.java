package com.demo.awesomepizza.service;

import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.rest.dto.OrderDto;

import java.util.List;

public interface OrderServiceI {
    OrderDto createOrder(OrderDto order);
    OrderDto updateOrder(OrderDto order);
    OrderDto updateOrderStatus(String id, OrderStatusEnum status) throws Exception;
    void deleteOrder(String id);
    OrderDto getOrder(String id) throws Exception;
    List<OrderDto> getOrderQueue();
}

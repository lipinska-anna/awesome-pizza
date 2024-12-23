package com.demo.awesomepizza.service.impl;

import com.demo.awesomepizza.domain.Order;
import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.repository.OrderRepository;
import com.demo.awesomepizza.rest.dto.OrderDto;
import com.demo.awesomepizza.service.OrderServiceI;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService implements OrderServiceI {

    private final Log LOG = LogFactory.getLog(OrderService.class);

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        String orderId = generateOrderId();
        orderDto.setId(orderId);
        orderDto.setCreatedOn(LocalDateTime.now());
        Order order = saveOrder(orderDto);
        LOG.debug(MessageFormat.format("Order created with id {0}", orderId));
        return mapOrderEntityToDto(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        if (Objects.equals(orderDto.getStatus(), OrderStatusEnum.READY)) {
            orderDto.setReadyTime(LocalDateTime.now());
        }
        Order order = saveOrder(orderDto);
        LOG.debug(MessageFormat.format("Updated order with id {0}", orderDto.getId()));
        return mapOrderEntityToDto(order);
    }

    @Override
    public OrderDto updateOrderStatus(String id, OrderStatusEnum status) throws Exception {
        OrderDto orderDto = getOrder(id);
        orderDto.setStatus(status);
        Order order = saveOrder(orderDto);
        LOG.debug(MessageFormat.format("Updated order {0} with id {1}", order.getId(), order.getStatus()));
        return mapOrderEntityToDto(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
        LOG.debug(MessageFormat.format("Deleted order with id {0}", id));
    }

    @Override
    public OrderDto getOrder(String id) throws Exception {
        return orderRepository.findById(id)
                .map(this::mapOrderEntityToDto)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<OrderDto> getOrderQueue() {
        return orderRepository.findAll().stream()
                .map(this::mapOrderEntityToDto)
                .collect(Collectors.toList());
    }

    private Order saveOrder(OrderDto orderDto) {
        Order order = mapOrderDtoToEntity(orderDto);
        orderRepository.save(order);
        return order;
    }

    private String generateOrderId() {
        List<String> uuidPortions = Arrays.asList(UUID.randomUUID().toString().split("-"));
        return uuidPortions.getLast().toUpperCase();
    }

    private Order mapOrderDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerName(orderDto.getCustomerName());
        order.setPrice(orderDto.getPrice());
        order.setStatus(orderDto.getStatus());
        order.setAllergensNote(orderDto.getAllergensNote());
        order.setCreatedOn(orderDto.getCreatedOn());
        order.setPizzaType(orderDto.getPizzaType());
        order.setReadyTime(orderDto.getReadyTime());
        return order;
    }

    private OrderDto mapOrderEntityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setCustomerName(order.getCustomerName());
        orderDto.setPrice(order.getPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setAllergensNote(order.getAllergensNote());
        orderDto.setCreatedOn(order.getCreatedOn());
        orderDto.setPizzaType(order.getPizzaType());
        orderDto.setReadyTime(order.getReadyTime());
        return orderDto;
    }
}

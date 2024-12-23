package com.demo.awesomepizza.service.impl;

import com.demo.awesomepizza.domain.Order;
import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.repository.OrderRepository;
import com.demo.awesomepizza.rest.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private OrderDto orderDto;
    private Order order;

    @BeforeEach
    void setUp() {
        orderDto = new OrderDto();
        orderDto.setId("1");
        orderDto.setCustomerName("John Doe");
        orderDto.setPrice(15.99);
        orderDto.setStatus(OrderStatusEnum.IN_PROGRESS);
        orderDto.setAllergensNote("None");
        orderDto.setPizzaType("Margherita");
        orderDto.setCreatedOn(LocalDateTime.now());

        order = new Order();
        order.setId("1");
        order.setCustomerName("John Doe");
        order.setPrice(15.99);
        order.setStatus(OrderStatusEnum.IN_PROGRESS);
        order.setAllergensNote("None");
        order.setPizzaType("Margherita");
        order.setCreatedOn(LocalDateTime.now());
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() {
        // Arrange
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        OrderDto result = orderService.createOrder(orderDto);

        // Assert
        assertNotNull(result);
        assertEquals(orderDto.getId(), result.getId());
        assertEquals(orderDto.getCustomerName(), result.getCustomerName());
        assertEquals(OrderStatusEnum.IN_PROGRESS, result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() {
        // Arrange
        orderDto.setStatus(OrderStatusEnum.READY);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        OrderDto result = orderService.updateOrder(orderDto);

        // Assert
        assertNotNull(result);
        assertEquals(orderDto.getStatus(), result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void updateOrderStatus_ShouldReturnUpdatedOrderWithNewStatus() throws Exception {
        // Arrange
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));
        orderDto.setStatus(OrderStatusEnum.READY);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        OrderDto result = orderService.updateOrderStatus("1", OrderStatusEnum.READY);

        // Assert
        assertNotNull(result);
        assertEquals(OrderStatusEnum.READY, result.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void deleteOrder_ShouldDeleteOrder() {
        // Arrange
        doNothing().when(orderRepository).deleteById(anyString());

        // Act
        orderService.deleteOrder("1");

        // Assert
        verify(orderRepository, times(1)).deleteById("1");
    }

    @Test
    void getOrder_ShouldReturnOrder() throws Exception {
        // Arrange
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        // Act
        OrderDto result = orderService.getOrder("1");

        // Assert
        assertNotNull(result);
        assertEquals(order.getId(), result.getId());
        assertEquals(order.getCustomerName(), result.getCustomerName());
        verify(orderRepository, times(1)).findById("1");
    }

    @Test
    void getOrder_ShouldThrowExceptionWhenOrderNotFound() {
        // Arrange
        when(orderRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> orderService.getOrder("non-existing-id"));
    }

    @Test
    void getOrderQueue_ShouldReturnListOfOrders() {
        // Arrange
        when(orderRepository.findAll()).thenReturn(java.util.List.of(order));

        // Act
        List<OrderDto> result = orderService.getOrderQueue();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(order.getId(), result.get(0).getId());
        verify(orderRepository, times(1)).findAll();
    }
}

package com.demo.awesomepizza.rest.impl;

import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.rest.dto.OrderDto;
import com.demo.awesomepizza.service.OrderServiceI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderServiceI orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderDto order;

    @BeforeEach
    void setUp() {
        // Create a mock order
        order = new OrderDto();
        order.setId("1");
        order.setStatus(OrderStatusEnum.NEW);
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() {
        // Arrange
        when(orderService.createOrder(any(OrderDto.class))).thenReturn(order);

        // Act
        ResponseEntity<OrderDto> response = orderController.createOrder(order);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).createOrder(any(OrderDto.class));
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() {
        // Arrange
        when(orderService.updateOrder(any(OrderDto.class))).thenReturn(order);

        // Act
        ResponseEntity<OrderDto> response = orderController.updateOrder(order);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).updateOrder(any(OrderDto.class));
    }

    @Test
    void updateOrderStatus_ShouldReturnUpdatedOrderWithNewStatus() throws Exception {
        // Arrange
        OrderStatusEnum newStatus = OrderStatusEnum.READY;
        order.setStatus(newStatus);
        when(orderService.updateOrderStatus(anyString(), any(OrderStatusEnum.class))).thenReturn(order);

        // Act
        ResponseEntity<OrderDto> response = orderController.updateOrderStatus("1", newStatus);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
        assertEquals(newStatus, response.getBody().getStatus());
        verify(orderService, times(1)).updateOrderStatus(anyString(), any(OrderStatusEnum.class));
    }

    @Test
    void deleteOrder_ShouldReturnOk() {
        // Arrange
        doNothing().when(orderService).deleteOrder(anyString());

        // Act
        ResponseEntity<Void> response = orderController.deleteOrder("1");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(orderService, times(1)).deleteOrder(anyString());
    }

    @Test
    void getOrder_ShouldReturnOrder() throws Exception {
        // Arrange
        when(orderService.getOrder(anyString())).thenReturn(order);

        // Act
        ResponseEntity<OrderDto> response = orderController.getOrder("1");

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(order, response.getBody());
        verify(orderService, times(1)).getOrder(anyString());
    }

    @Test
    void getOrderQueue_ShouldReturnOrderList() {
        // Arrange
        List<OrderDto> orders = Arrays.asList(order);
        when(orderService.getOrderQueue()).thenReturn(orders);

        // Act
        ResponseEntity<List<OrderDto>> response = orderController.getOrderQueue();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(orders, response.getBody());
        verify(orderService, times(1)).getOrderQueue();
    }
}
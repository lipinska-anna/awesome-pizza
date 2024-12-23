package com.demo.awesomepizza.rest.impl;

import com.demo.awesomepizza.domain.enums.OrderStatusEnum;
import com.demo.awesomepizza.rest.OrderControllerI;
import com.demo.awesomepizza.rest.dto.OrderDto;
import com.demo.awesomepizza.service.OrderServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController implements OrderControllerI {

    private final OrderServiceI orderService;

    public OrderController(OrderServiceI orderService) {
        this.orderService = orderService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) {
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto order) {
        return ResponseEntity.ok().body(orderService.updateOrder(order));
    }

    @Override
    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable String id, @PathVariable OrderStatusEnum status) throws Exception {
        return ResponseEntity.ok().body(orderService.updateOrderStatus(id, status));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @Override
    @GetMapping("/queue")
    public ResponseEntity<List<OrderDto>> getOrderQueue() {
        return ResponseEntity.ok().body(orderService.getOrderQueue());
    }

}

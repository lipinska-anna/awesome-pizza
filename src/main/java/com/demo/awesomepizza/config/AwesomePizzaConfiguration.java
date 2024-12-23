package com.demo.awesomepizza.config;

import com.demo.awesomepizza.repository.OrderRepository;
import com.demo.awesomepizza.service.OrderServiceI;
import com.demo.awesomepizza.service.impl.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwesomePizzaConfiguration {
    @Bean
    public OrderServiceI orderService(OrderRepository orderRepository) {
        return new OrderService(orderRepository);
    }
}

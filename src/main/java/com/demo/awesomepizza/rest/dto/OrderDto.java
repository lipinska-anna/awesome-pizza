package com.demo.awesomepizza.rest.dto;

import com.demo.awesomepizza.domain.enums.OrderStatusEnum;

import java.time.LocalDateTime;

public class OrderDto {

    private String id;
    private String customerName;
    private String pizzaType;
    private Double price;
    private String allergensNote;
    private OrderStatusEnum status;
    private LocalDateTime createdOn;
    private LocalDateTime readyTime;

    public OrderDto() {
    }

    public OrderDto(String id, String customerName, String pizzaType, Double price, String allergensNote, OrderStatusEnum status, LocalDateTime createdOn, LocalDateTime readyTime) {
        this.id = id;
        this.customerName = customerName;
        this.pizzaType = pizzaType;
        this.price = price;
        this.allergensNote = allergensNote;
        this.status = status;
        this.createdOn = createdOn;
        this.readyTime = readyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAllergensNote() {
        return allergensNote;
    }

    public void setAllergensNote(String allergensNote) {
        this.allergensNote = allergensNote;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(LocalDateTime readyTime) {
        this.readyTime = readyTime;
    }
}

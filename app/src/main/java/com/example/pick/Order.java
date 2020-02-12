package com.example.pick;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_table")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String orderName;
    private int orderQuantity;
    private String clientPhoneNumber;
    private String clientPickUpLocation;

    public Order(String orderName,
                 int orderQuantity,
                 String clientPhoneNumber,
                 String clientPickUpLocation) {
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientPickUpLocation = clientPickUpLocation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public String getClientPickUpLocation() {
        return clientPickUpLocation;
    }
}

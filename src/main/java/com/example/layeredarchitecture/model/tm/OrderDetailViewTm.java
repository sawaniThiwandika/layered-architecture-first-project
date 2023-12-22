package com.example.layeredarchitecture.model.tm;

import java.time.LocalDate;

public class OrderDetailViewTm {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCusId() {
        return cusId;
    }

    public String getAddress() {
        return address;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderDetailViewTm(String id, String name, String cusId, LocalDate date,String address) {
        this.id = id;
        this.name = name;
        this.cusId = cusId;
        this.date = date;
        this.address=address;
    }

    private String address;
    private String cusId;
    private LocalDate date;
    public OrderDetailViewTm() {
    }

}

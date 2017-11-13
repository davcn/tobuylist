package com.davcn.tobuylist.productsservice.model;

public class Product {

    private long id;
    private String name;
    private long price;
    private String addedBy;

    public Product(long id, String name, long price, String addedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addedBy = addedBy;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}

package com.davcn.tobuylist.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "products", catalog = "testdb")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private long price;

    @Column(name = "addedBy")
    private String addedBy;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Product(long id, String name, long price, String addedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.addedBy = addedBy;
    }

    public Product(String name, long price, String addedBy) {
        this(0, name, price, addedBy);
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

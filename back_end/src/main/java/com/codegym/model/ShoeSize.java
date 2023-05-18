package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ShoeSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean isDelete;
    private Integer quantity;
    @JsonIgnore
    @OneToMany(mappedBy = "shoeSize")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "shoe_id", referencedColumnName = "id")
    private Shoe shoe;

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size size;

    public ShoeSize() {
    }

    public ShoeSize(Integer id, boolean isDelete, Integer quantity, Set<OrderDetail> orderDetails, Shoe shoe, Size size) {
        this.id = id;
        this.isDelete = isDelete;
        this.quantity = quantity;
        this.orderDetails = orderDetails;
        this.shoe = shoe;
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}

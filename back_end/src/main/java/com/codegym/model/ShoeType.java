package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;
@Entity
public class ShoeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;

    @JsonIgnore
    @OneToMany(mappedBy = "shoeType")
    private Set<Shoe> shoes;

    public ShoeType() {
    }

    public ShoeType(Integer id, String name, boolean isDelete, Set<Shoe> shoes) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.shoes = shoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Set<Shoe> getShoes() {
        return shoes;
    }

    public void setShoes(Set<Shoe> shoes) {
        this.shoes = shoes;
    }
}

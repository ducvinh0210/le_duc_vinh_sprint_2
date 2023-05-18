package com.codegym.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer price;
    private Integer discount;
    private String manufacturer;
    private String image;
    private String describes;
    private boolean isDelete;
    @JsonIgnore
    @OneToMany(mappedBy = "shoe")
    private Set<ShoeSize> shoeSizes;

    @ManyToOne
    @JoinColumn(name = "shoe_type_id", referencedColumnName = "id")
    private ShoeType shoeType;

    public Shoe() {
    }

    public Shoe(Integer id, String name, Integer price, Integer discount, String manufacturer, String image, String describes, boolean isDelete, Set<ShoeSize> shoeSizes, ShoeType shoeType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.manufacturer = manufacturer;
        this.image = image;
        this.describes = describes;
        this.isDelete = isDelete;
        this.shoeSizes = shoeSizes;
        this.shoeType = shoeType;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Set<ShoeSize> getShoeSizes() {
        return shoeSizes;
    }

    public void setShoeSizes(Set<ShoeSize> shoeSizes) {
        this.shoeSizes = shoeSizes;
    }

    public ShoeType getShoeType() {
        return shoeType;
    }

    public void setShoeType(ShoeType shoeType) {
        this.shoeType = shoeType;
    }
}

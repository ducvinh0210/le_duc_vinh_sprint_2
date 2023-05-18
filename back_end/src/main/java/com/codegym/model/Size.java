package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean isDelete;
    @OneToMany(mappedBy = "size")
    private Set<ShoeSize> shoeSizes;


    public Size() {
    }

    public Size(Integer id, String name, boolean isDelete, Set<ShoeSize> shoeSizes) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
        this.shoeSizes = shoeSizes;
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

    public Set<ShoeSize> getShoeSizes() {
        return shoeSizes;
    }

    public void setShoeSizes(Set<ShoeSize> shoeSizes) {
        this.shoeSizes = shoeSizes;
    }
}

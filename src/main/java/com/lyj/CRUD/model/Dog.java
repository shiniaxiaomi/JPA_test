package com.lyj.CRUD.model;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/24 15:55
 */

@Entity
public class Dog {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @ManyToOne
    private Owner owner;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

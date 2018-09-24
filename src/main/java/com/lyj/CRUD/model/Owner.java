package com.lyj.CRUD.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/24 15:55
 */

@Entity
public class Owner {//主人

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "owner",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Dog> dogs=new HashSet<>();

    public Owner() {
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

    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dogs=" + dogs +
                '}';
    }
}

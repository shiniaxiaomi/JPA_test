package com.lyj.CRUD.select;

/**
 * Created by 陆英杰
 * 2018/9/24 18:10
 */
public class DTO {

    Integer id;

    String name;

    String dogName;

    public DTO() {
    }

    public DTO(Integer id, String name, String dogName) {
        this.id = id;
        this.name = name;
        this.dogName = dogName;
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

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dogName='" + dogName + '\'' +
                '}';
    }
}

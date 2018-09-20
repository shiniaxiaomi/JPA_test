package com.lyj.springboot.mapping.entity;



import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/19 19:55
 */

@Entity
@Table(name = "db_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private Integer id;

    private String name;

    @Column //省略默认列名就是属性名
    private Integer age;


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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

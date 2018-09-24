package com.lyj.springboot.mapping.oneToOne;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class User1 {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    /**
     * @OneToOne: 一对一关系映射
     *      1. 在一对一关联关系中,外键字段不需设置为unique = true,保证外键的值不能重复
     */
    @JoinColumn(unique = true)
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Task1 task1;

    public User1() {
    }

    public User1(String name) {
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

    public Task1 getTask1() {
        return task1;
    }

    public void setTask1(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", task1=" + task1.getId()+","+task1.getTaskName() +
                '}';
    }
}

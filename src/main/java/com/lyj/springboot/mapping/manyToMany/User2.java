package com.lyj.springboot.mapping.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class User2 {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;

    /**
     * @ManyToMany: 设置多对多关系映射
     *      1.mappedBy = "users": 表示User是被维护端,外键在Task表中
     */
    @ManyToMany(mappedBy = "users",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Task2> tasks=new HashSet<>();

    public User2() {
    }

    public User2(Integer id,String name) {
        this.id=id;
        this.name = name;
    }

    public User2(String name) {
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

    public Set<Task2> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task2> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

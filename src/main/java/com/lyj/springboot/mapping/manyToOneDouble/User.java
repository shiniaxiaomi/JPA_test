package com.lyj.springboot.mapping.manyToOneDouble;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class User {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    /**
     * @OneToMany: 说明该类User是一的一端(One),Task是多的一端(ToMany)
     *
     * 如果mappedBy存在这个类中,那么这个类就是被维护端,外键就在另外的一个类中(不在该类中),然后外键的值就是该类的id
     *      1. mappedBy = "user": 指定了 以被标注的字段属性的类中(Task类)的 User user 属性的id作为外键
     */
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Task> tasks=new HashSet<>();

    public User() {
    }

    public User(String name) {
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks.size() +
                '}';
    }
}

package com.lyj.springboot.mapping.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class Task2 {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String taskName;

    /**
     * @ManyToMany: 设置多对多关系映射
     *      1. 维护端能够操作外键,即删除之后会去删除中间表的关联关系,
     *         而被维护端没有权利去操作中间表的关联关系
     *      2. @JoinTable: 设置中间表
     *          1). name = "user2_task2": 设置中间表的名称
     *          2). joinColumns: 设置维护端的外键关系映射(Task的id)
     *          3). inverseJoinColumns: 设置被维护端的外键关系映射(User的id)
     */
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user2_task2",joinColumns=@JoinColumn(name = "task_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User2> users = new HashSet<>();

    public Task2() {
    }

    public Task2(String taskName) {
        this.taskName = taskName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Set<User2> getUsers() {
        return users;
    }

    public void setUsers(Set<User2> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        return "Task2{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", users=" + users +
                '}';
    }
}

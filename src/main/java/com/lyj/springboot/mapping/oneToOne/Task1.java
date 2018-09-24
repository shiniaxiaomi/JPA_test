package com.lyj.springboot.mapping.oneToOne;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class Task1 {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String taskName;

    /**
     * @OneToOne: 一对一关系映射
     *      mappedBy: 标注在该类中,则说明该类是被维护端,User是关系维护端,外键在User那张表中
     */
    @OneToOne(mappedBy = "task1",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User1 user1;

    public Task1() {
    }

    public Task1(String taskName) {
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

    public User1 getUser1() {
        return user1;
    }

    public void setUser1(User1 user1) {
        this.user1 = user1;
    }

    @Override
    public String toString() {
        return "Task1{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", user1=" + user1.getName() +
                '}';
    }
}

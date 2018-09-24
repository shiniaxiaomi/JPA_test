package com.lyj.springboot.mapping.manyToOneDouble;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/22 0:02
 */

@Entity
public class Task {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String taskName;

    /**
     * 使用@ManyToOne进行标注: 说明该类Task是多的一端(Many),User是一的一端(ToOne)
     *      1. cascade: 设置级联属性,当对该类(Task)进行保存,删除,更新操作的时候,这个对应的级联更新才会生效
     *          1). PERSIST: 级联保存 (一般开启)
     *          2). REMOVE: 级联删除
     *          3). MERGE: 级联更新 (一般开启)
     *          4). REFRESH: 级联刷新
     *      2. fetch: 设置加载策略
     *          1). FetchType.EAGER: 立即加载(一的一端默认是这个策略)
     *          2). FetchType.LAZY: 延迟加载(多的一端默认是这个策略)
     *      3. optional: 设置外键能够为空
     *          1). true: 默认是可以为空
     *          2). false: 外键不能为空
     *
     * 如果该类没有mappedBy属性,那么则表示这个注解上的这个字段就是外键字段,也就表示了这个类是维护端(一般把多的一端作为是维护端)
     */
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

    public Task() {
    }

    public Task(String taskName) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

package com.lyj.springboot.mapping.entity;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/20 14:04
 */

@Table(name = "db_orders")
@Entity
public class Order {

    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    @Id
    private Integer id;
    private String orderName;


    /**
     * 对于多对一的理解:
     *      1.如果@ManyToOne写在了Order类里,则表明Order是多的一端
     *      2.被@ManyToOne标注的Customer就是一的一端
     *
     *      主要看to什么,如果toOne,则被标注的字段就是一的一端
     *                 如果是toMany,则被标注的字段就是多的一端
     *
     * @ManyToOne选填参数:
     *      1.cascade: 设置级联属性,比如级联删除等(cascade = CascadeType.REMOVE)
     *      2.optional: 设置关联查询,比如左连接等(optional = true,默认的为左连接)
     *      3.fetch: 设置加载策略,比如懒加载等(fetch = FetchType.LAZY)\
     *
     * @JoinColumn(name = "customer_id"):设置对应的外键
     *
     */
    //映射单项 多对一 的关联关系
    //使用@ManyToOne 来映射多对一的关联关系
    //使用 @JoinColumn来映射外键

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", customer=" + customer +
                '}';
    }
}

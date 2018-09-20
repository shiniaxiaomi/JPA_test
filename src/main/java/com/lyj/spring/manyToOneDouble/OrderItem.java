package com.lyj.spring.manyToOneDouble;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/20 21:38
 */


@Entity
public class OrderItem {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;

    //设置长度为40,不能为空
    @Column(length = 40,nullable = false)
    private String productName;

    @Column(nullable = false)
    private Float price;

    /**
     * @ManyToOne: 默认不使用延时加载
     * cascade: 设置级联属性
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    //定义外键,并指定外键名称
    @JoinColumn(name = "order_id")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public OrderItem(String productName, Float price) {
        this.productName = productName;
        this.price = price;
    }

    public OrderItem() {
    }
}

package com.lyj.spring.manyToOneDouble;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/20 21:37
 */

@Entity
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private Float amount=0f;//设置默认值

    /**
     * 当为双向关系时,多的一方为关系维护端: 即由OrderItem来维护 外键
     * mappedBy: (很重要)
     *          1: 可以这么理解,即mappedBy在哪个类,则这个类的主键就和其他表的外键相关联(然后外键就会在其他表中)------[很容易理解]
     *          2. 如果mappedBy = "order",则Order order成员变量所在的类所映射的表中有外键,关联的是则Order类中的主键(id)----[很重要]
     * @OneToMany 默认使用的是延迟加载
     * cascade: 设置级联属性
     */
    @OneToMany(mappedBy="order",cascade = CascadeType.ALL)
    private Set<OrderItem> items=new HashSet<>();

    public void addOrderItem(OrderItem item){
        item.setOrder(this);//多的一端进行关系维护,就是把item属于哪个订单里面给维护进去
        this.items.add(item);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}

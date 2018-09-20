package com.lyj.spring.manyToOneDouble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by lyj80 on 2018/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void before(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();//开始事务
    }
    @After
    public void after(){
        entityManager.getTransaction().commit();//提交事务
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void save(){
        Order order=new Order();
        order.setAmount(34f);

        OrderItem item1=new OrderItem("aaa",50f);
        OrderItem item2=new OrderItem("bbb",25f);

        order.addOrderItem(item1);
        order.addOrderItem(item2);

        /**
         * 如果是双向关系的话,设置好级联属性之后就直接保存一的一端即可
         */
        entityManager.persist(order);
    }

}
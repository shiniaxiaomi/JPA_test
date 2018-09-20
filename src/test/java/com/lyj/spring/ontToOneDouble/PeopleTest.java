package com.lyj.spring.ontToOneDouble;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.Assert.*;

/**
 * Created by lyj80 on 2018/9/21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleTest {
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
        //在一对一双向映射中,如果关系维护端(即有外键的)已经设置了外键不能为null,则就可以直接进行保存关系维护段
        //因为jpa会先关系被维护端(IDCard)保存之后,得到的id值再去保存关系维护端(id就存在外键中)
        People people=new People("sds",new IDCard("2222222"));//设置people和idCard的关系
        entityManager.persist(people);

//        //如果什么都不设置,在双方都设置一下关系,是比较保险的
//        IDCard idCard = new IDCard("11111111111111");
//        People people1=new People("sds",idCard);//设置people和idCard的关系
//        idCard.setPeople(people1);//设置idCard和people的关系
//        //在一对一双向映射中,在双向关系都设置好之后,进行保存任何一端都可以(要先设置好级联属性)
//        entityManager.persist(people1);
    }
}
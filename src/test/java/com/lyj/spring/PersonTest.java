package com.lyj.spring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import static org.junit.Assert.*;

/**
 * Created by lyj80 on 2018/9/20.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

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


    //insert
    @Test
    public void save(){
        Person person=new Person();
        person.setName("sss");
        entityManager.persist(person);
    }


    //select(没有延时加载)
    @Test
    public void getPerson(){
        Person person = entityManager.find(Person.class, 1);
        System.out.println(person+":"+person.getName());
    }

    //select(延时加载,首先返回一个代理对象,当要获取属性的时候,在去数据库中查询数据)
    @Test
    public void getPerson2(){
        Person person = entityManager.getReference(Person.class, 1);
//        System.out.println(person+":"+person.getName());//当执行这句语句的时候,控制台才会打印sql语句
    }

    //update
    @Test
    public void updatePerson(){
        Person person = entityManager.find(Person.class, 1);//这个获取到的对象是一个游离状态
        person.setName("aaa");
        entityManager.merge(person);//当要持久化游离状态的对象时,就要使用merge,然后在提交事务,才能够被持久化
    }


    //delete
    @Test
    public void deletePserson(){
        Person person = entityManager.find(Person.class, 1);//这个获取到的对象是一个游离状态
        entityManager.remove(person);
    }


    //select:使用sql
    @Test
    public void query(){
        Query query = entityManager.createQuery("select o from Person o where o.id=?1",Person.class);
        query.setParameter(1,2);
        Person person = (Person) query.getSingleResult();
        System.out.println(person);
    }

    //delete:使用sql
    @Test
    public void delete(){
        Query query = entityManager.createQuery("delete from Person o where o.id=?1");
        query.setParameter(1,2);
        query.executeUpdate();
    }

    //update:使用sql
    @Test
    public void update(){
        Query query = entityManager.createQuery("update Person set name=:name where id=:id");
        query.setParameter("name","xxx");
        query.setParameter("id",1);
        query.executeUpdate();
    }

}
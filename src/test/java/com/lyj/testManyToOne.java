package com.lyj;

import com.lyj.springboot.mapping.entity.Customer;
import com.lyj.springboot.mapping.entity.Order;
import com.lyj.springboot.mapping.repositories.CustomerDao;
import com.lyj.springboot.mapping.repositories.OrderDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/20 14:08
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class testManyToOne {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    //保存
    @Test
    public void test1(){
        Customer customer=new Customer();
        customer.setAge(19);
        customer.setName("aa");

        Order order1=new Order();
        order1.setOrderName("1");
        order1.setCustomer(customer);

        Order order2=new Order();
        order2.setOrderName("2");
        order2.setCustomer(customer);

        //在保存多对一时,先保存1的一端,在保存多的一端
        customerDao.save(customer);
        orderDao.saveAll(Arrays.asList(order1,order2));
    }

    //默认情况下,使用左连接的方式来获取多的一端的对象和其关联的一的一端的对象
    //查询
    @Test
    public void test2(){
        Optional<Order> order = orderDao.findById(2);
        System.out.println(order.get().getOrderName());
        System.out.println(order.get().getCustomer().getName());
    }

    //查询
    @Test
    public void test3(){
        List<Order> list = orderDao.findOrOrderByOrderNameLike("1%");
        for(Order order:list){
            System.out.println(order);
        }
    }

    //删除多的一端
    @Test
    public void deleteMany(){
        orderDao.deleteById(4);
    }

    //删除一的一端
    @Test
    public void deleteOne(){
        customerDao.deleteById(3);
    }

    //改
    @Test
    public void update(){
//        Order order=new Order();
//        order.setId(2);
//        order.setOrderName("2222");
//        Customer customer = new Customer();
//        customer.setName("111");
//        customer.setAge(113);
//        order.setCustomer(customer);
//        orderDao.saveAndFlush(order);

//        Order order=new Order();
//        order.setOrderName("sss");
//        order.setId(2);
//        orderDao.saveAndFlush(order);

//        Order order=new Order();
//        order.setOrderName("sss");
//        orderDao.



//        Order one = orderDao.getOne(2);
//        System.out.println(one);
//        one.setOrderName("1111");
//        Order order = orderDao.save(one);
//        System.out.println(order);


        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        Order order = entityManager.find(Order.class, 2);
//        System.out.println(order);
        entityManager.getTransaction().begin();
        Order order = entityManager.find(Order.class, 2);
        order.setOrderName("555");
        Customer customer = new Customer();
        customer.setId(1);
        customer.setAge(1);
        customer.setName("333333333");
        order.setCustomer(customer);
        entityManager.merge(order);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

}

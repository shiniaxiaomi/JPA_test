package com.lyj.springboot.mapping.manyToMany;

import com.lyj.springboot.mapping.manyToOneDouble.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * Created by lyj80 on 2018/9/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class User2Test {
    @Autowired
    MyService service;

    @Test
    public void save(){
        service.save();
    }

    @Test
    public void deleteTask(){
        service.deleteTask();
    }

    @Test
    public void deleteUser(){
        service.deleteUser();
    }

    @Test
    public void deleteAll(){
        service.deleteAll();
    }

    @Test
    public void update(){
        service.update();
    }

    @Test
    public void selectByExampleMatcher(){
        service.selectByExampleMatcher();
    }

    @Test
    public void selectByPage(){
        service.selectByPage();
    }


    @Test
    public void test(){
        service.test();
    }

    @Test
    public void test1(){
        service.test1();
    }


}
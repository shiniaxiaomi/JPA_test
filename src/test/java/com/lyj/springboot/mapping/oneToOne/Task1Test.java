package com.lyj.springboot.mapping.oneToOne;

import com.lyj.springboot.mapping.manyToOneDouble.Task;
import com.lyj.springboot.mapping.manyToOneDouble.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by lyj80 on 2018/9/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class Task1Test {

    @Autowired
    OneToOneService service;

    @Test
    public void save(){
        service.save();
    }

    @Test
    public void deleteUser(){
        service.deleteUser();
    }

    @Test
    public void deleteTask(){
        service.deleteTask();
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
    public void selectTaskByUser(){
        service.selectTaskByUser();
    }


}
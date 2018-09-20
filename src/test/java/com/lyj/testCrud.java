package com.lyj;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.service.UserCrudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/20 9:48
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class testCrud {

    @Autowired
    UserCrudService crudService;


    @Test
    public void test1(){
        List<User> users=new ArrayList<>();
        for(int i=0;i<10;i++){
            users.add(new User(String.valueOf(i),String.valueOf(i)));
        }
        crudService.savaUsers(users);
    }

    @Test
    public void test2(){
        Optional<User> users = crudService.findById(2);
        System.out.println(users.get());
    }

    @Test
    public void test3(){
        crudService.deleteById(7);
    }

}

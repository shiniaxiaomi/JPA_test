package com.lyj;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.repositories.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/20 10:34
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class testJpa {

    @Autowired
    UserJpaRepository jpaRepository;


    //insert
    @Test
    public void test1(){
        User user=new User("111","222");
        jpaRepository.saveAndFlush(user);
    }

    //update
    @Test
    public void test2(){
        User user=new User("111","222222");
        user.setId(2);//设置id之后就可以进行修改
        User user1 = jpaRepository.saveAndFlush(user);
        System.out.println(user1);
    }

    //使用Example.of(user)可以方便的查找匹配的属性
    @Test
    public void test3(){
        User user=new User();
        user.setPassword("31");
        user.setUserName("3");

        Example<User> example = Example.of(user);
        Optional<User> one = jpaRepository.findOne(example);
        System.out.println(one.get());

    }

}

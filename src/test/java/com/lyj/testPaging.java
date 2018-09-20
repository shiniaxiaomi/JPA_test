package com.lyj;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.repositories.UserPagingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 陆英杰
 * 2018/9/20 10:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class testPaging {

    @Autowired
    UserPagingRepository pagingRepository;

    @Test
    public void test1(){
        Sort orders = Sort.by(Sort.Order.desc("id"), Sort.Order.asc("password"));//封装了排序的信息,先按id降序,再按password升序排列
        PageRequest pageRequest = PageRequest.of(1, 4, orders);//封装了分页的信息,页数,个数,和排序信息
        Page<User> page = pagingRepository.findAll(pageRequest);

        System.out.println("总记录数:"+page.getTotalElements());
        System.out.println("当前第几页:"+page.getNumber());
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("当前页面的List:"+page.getContent());
        System.out.println("当前页面的记录数"+page.getNumberOfElements());
    }

}

package com.lyj.springboot.mapping.oneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/22 16:30
 */

@Transactional //为全部方法加上事务
@Service
public class OneToOneService {
    @Autowired
    UserDao1 userDao1;
    @Autowired
    TaskDao1 taskDao1;

    //增
    public void save(){
        Task1 task1=new Task1();
        task1.setTaskName("222222bbbcc");

        User1 user1=new User1();
        user1.setName("111111aaaccc");

        //在一对一关联关系中,谁设置关系并保存都没有关系
        //设置关系并使用级联保存---方法一
        //user1.setTask1(task1);
        //userDao1.save(user1);//使用级联保存
        //设置关系并使用级联保存---方法二
        task1.setUser1(user1);
        taskDao1.save(task1);//使用级联保存
    }

    //删除User(User是有外键的一方)
    //jpa本身不能够实现删除外键数据存在,但是直接删除掉数据的情况,所以要实现直接删除User,就要用原生的sql
    public void deleteUser(){
        userDao1.deleteUser1ById(5);

    }
    //删除Task
    public void deleteTask(){
        taskDao1.deleteById(3);
    }
    //删除全部(要先把外键所关联的数据删除掉,然后才能够删除要目标数据)
    public void deleteAll(){
        //通过task删除全部(推荐,先删除有外键的,也就是User)
        Optional<Task1> task1 = taskDao1.findById(1);//通过找到id为1的task
        userDao1.delete(task1.get().getUser1());//删除从task中获取到的user
        taskDao1.delete(task1.get());//删除task

        //通过User删除全部(会多出一条update语句)
//        Optional<User1> user1 = userDao1.findById(2);
//        taskDao1.delete(user1.get().getTask1());
//        userDao1.delete(user1.get());
    }

    //改
    public void update(){
        //使用级联更新(通过user更新task)
        Optional<User1> user1 = userDao1.findById(5);//通过先找到id=6的user,然后在修改user的属性,并保存,达到更新的效果
        user1.get().getTask1().setTaskName("555");
        userDao1.saveAndFlush(user1.get());

        //使用级联更新(通过task更新user)
//        Optional<Task1> task1 = taskDao1.findById(5);
//        task1.get().getUser1().setName("555");
//        taskDao1.saveAndFlush(task1.get());
    }

    //查
    public void selectTaskByUser(){
        Optional<User1> user1 = userDao1.findById(5);
        System.out.println(user1);
    }
}

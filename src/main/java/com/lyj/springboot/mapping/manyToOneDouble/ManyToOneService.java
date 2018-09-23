package com.lyj.springboot.mapping.manyToOneDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/22 15:46
 */

//@Transactional  //@Transactional 如果加在类上时,该类的所有方法都会被加上事务
@Service
public class ManyToOneService {
    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;

    //增
    @Transactional
    public void save(){
        User user=new User("1");
        Task task1=new Task("2");
        Task task2=new Task("3");
        //设置双方关系,并保存
        user.getTasks().add(task1);
        user.getTasks().add(task2);
        task1.setUser(user);
        task2.setUser(user);
        //保存user(使用级联保存,直接操作一的一端可以)
        userDao.save(user);
    }

    //删除User(一)
    @Transactional
    public void deleteUser(){
        userDao.deleteById(1);
    }
    //删除Task(多)
    @Transactional
    public void deleteTask(){
        taskDao.deleteById(1);
    }
    //删除全部
    @Transactional
    public void deleteAll(){
        Optional<User> user = userDao.findById(1);//从id找到user
        Set<Task> tasks = user.get().getTasks();//得到与user对应的多个task对象

        taskDao.deleteAll(tasks);//先删除多的一端,即维护端(有外键的一端)
        userDao.deleteById(1);
    }

    //更改:通过级联属性 (通过Task更改User)
    @Transactional
    public void updateUser1(){
        Optional<Task> task = taskDao.findById(3);//先找到id为3的task
        task.get().getUser().setName("111111111111");//然后通过task获取user,并修改user属性中的值
        taskDao.saveAndFlush(task.get());//设置了级联更新,可以通过task修改关联的user属性
    }
    //更改:不通过级联属性(通过Task更改User)
    @Transactional
    public void updateUser2(){
        Optional<Task> task = taskDao.findById(3);//先找到id为3的task
        User user = task.get().getUser();//然后通过task获取user
        user.setName("22222");//修改user属性中的值
        userDao.save(user);//保存或者更新user
    }

    //查询(通过User查询Task)
    @Transactional
    public void selectTaskByUser(){
        Optional<User> user = userDao.findById(2);//直接可以进行关联查询
        System.out.println(user.get());
    }

}

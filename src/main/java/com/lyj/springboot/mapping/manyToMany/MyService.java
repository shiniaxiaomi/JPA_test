package com.lyj.springboot.mapping.manyToMany;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/22 15:15
 */

@Transactional
@Service
public class MyService {
    @Autowired
    UserDao2 userDao2;
    @Autowired
    TaskDao2 taskDao2;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    //增
    public void save(){
        User2 user1=new User2("aaa");
        User2 user2=new User2("bbb");
        Task2 task1=new Task2("111");
        Task2 task2=new Task2("222");

        //设置关联关系
        user1.getTasks().add(task1);
        user1.getTasks().add(task2);
        user2.getTasks().add(task1);
        user2.getTasks().add(task2);

        task1.getUsers().add(user1);
        task1.getUsers().add(user2);
        task2.getUsers().add(user1);
        task2.getUsers().add(user2);

        //保存,只要有一方进行保存即可,因为设置了级联保存
        taskDao2.save(task1);
        taskDao2.save(task2);
    }

    //删除
    public void deleteTask(){
        taskDao2.deleteById(1);//直接删除id为1的task,并且删除掉中间表中的关联关系(task_id=1)
    }
    //删除
    public void deleteUser(){
        userDao2.deleteById(1);//只是删除user表中id=1的数据,它没有权利去操作中间表,因为他是被维护端
    }
    //删除
    public void deleteAll(){
        Optional<Task2> task2 = taskDao2.findById(1);//先找到id为1的task
        userDao2.deleteAll(task2.get().getUsers());//然后删除task所关联的所有user
        taskDao2.delete(task2.get());//最后删除task
    }

    //修改(通过id进行更改)
    public void update(){
        //更改task
//        Optional<Task2> task2 = taskDao2.findById(2);
//        task2.get().setTaskName("wwww");
//        taskDao2.save(task2.get());
        //更改user
        Optional<User2> user2 = userDao2.findById(1);
        user2.get().setName("wwwwww");
        userDao2.save(user2.get());
    }

    /**
     * 多对多查询理解:
     *      1.一般的业务逻辑就是先把一端的查询出来,然后展示出来
     *      2.用户选择了其中一条,然后就拿这一条的id值再去查询另外一端的数据
     */
    //查询(可以过滤一些不需要查询的属性,比如id等)
    public void selectByExampleMatcher(){
        ExampleMatcher matcher = ExampleMatcher.matching(); //构建对象
        matcher.withIgnorePaths("id");  //忽略id属性

        Task2 task2 = new Task2("12");
        Example<Task2> example = Example.of(task2, matcher);

        List<Task2> task2s = taskDao2.findAll(example);
        System.out.println(task2s);
    }

    //分页查询
    public void selectByPage(){
        PageRequest pageRequest = PageRequest.of(0, 4);//封装了分页的信息,页数,个数,和排序信息
        Page<Task2> page = taskDao2.findAll(pageRequest);
        System.out.println(page.getContent());
    }

    //通过高级的查询对象查询数据 Specification(可以进行单表的动态条件查询,也可以进行关联多表查询)
    public void test(){
        Specification<Task2> specification=new Specification<Task2>() {
            @Override
            public Predicate toPredicate(Root<Task2> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate id = criteriaBuilder.equal(root.get("id"), 1);
                Predicate id1 = criteriaBuilder.equal(root.get("id"), 2);
                Predicate ids = criteriaBuilder.or(id,id1);//将条件用或连接(查询id为1或者2)

                Predicate task_name = criteriaBuilder.like(root.get("taskName"), "%ww%");//查询taskName像WW的

                Predicate and = criteriaBuilder.and(ids, task_name);//将ids的条件和task_name的条件用and接连起来

                //关联user表
                Join<Object, Object> join = root.join("users");//关联users表,返回一个Join对象
                Predicate users = criteriaBuilder.equal(join.get("id"), 1);//通过join对象添加关联表的条件

                criteriaQuery.where(and,users);
                return null;
            }
        };

        List<Task2> list = taskDao2.findAll(specification);
        for(int i=0;i<list.size();i++){
            Task2 task2 = list.get(i);
            System.out.println(task2);
        }

    }

    //关联多表查询
    public void test1(){
        Specification specification= new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join task2 = root.join("task2");
                Join user2 = root.join("user2");

                Predicate taskName = criteriaBuilder.like(task2.get("taskName"), "%ww%");
                Predicate name = criteriaBuilder.like(user2.get("name"), "%ww%");

                Predicate and = criteriaBuilder.and(task2, name);
                criteriaQuery.where(and);

                return null;
            }
        };

        List<Task2> list = taskDao2.findAll(specification);
        for(int i=0;i<list.size();i++){
            Task2 task2 = list.get(i);
            System.out.println(task2);
        }

    }



}

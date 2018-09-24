package com.lyj.util;

import com.lyj.springboot.mapping.manyToMany.Task2;
import com.lyj.springboot.mapping.oneToOne.Task1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by lyj80 on 2018/9/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityManagerUtilTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;


    //测试HQL语句
    @Test
    public void test(){
        EntityManager session = entityManagerFactory.createEntityManager();
        session.getTransaction().begin();

        //使用HQL语句查询
        Query query = session.createQuery("select t from Task1 t where t.id=5");
        Task1 result = (Task1) query.getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();

    }

    //测试原生的sql
    @Test
    public void test1(){
        EntityManager session = entityManagerFactory.createEntityManager();
        session.getTransaction().begin();

        //使用原生sql
        Query query = session.createNativeQuery("select t.id,t.task_name from Task1 t where t.id=5",Task1.class);
        Task1 result = (Task1) query.getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();

    }


    //使用QBC(query by Criteria)
    @Test
    public void test2(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task2> query = criteriaBuilder.createQuery(Task2.class);

        Root<Task2> task1Root = query.from(Task2.class);
        query.select(task1Root);

        Predicate id = criteriaBuilder.equal(task1Root.get("id"), 1);



        query.where(id);

        List<Task2> list = entityManager.createQuery(query).getResultList();
        for(Task2 task2:list){
            System.out.println(task2);
        }

        entityManager.getTransaction().commit();




    }

    @Test
    public void test3(){


    }


    private class MySpec implements Specification<Task2> {

        @Override
        public Predicate toPredicate(Root<Task2> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

            //1.混合条件查询
            Path<String> exp1 = root.get("id");
            Path<String> exp2 = root.get("task_name");
            query.where(cb.equal(exp1, "1"), cb.like(exp2, "%ww%"));

            //2.多表查询
                /*Join<ImTeacher,ImStudent> join = root.join("imStudent", JoinType.INNER);
                Path<String> exp3 = join.get("name");
                return cb.like(exp3, "%jy%");*/

            return null;

        }
    }


}
package com.lyj.CRUD.insert;

import com.lyj.CRUD.model.Dog;
import com.lyj.CRUD.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by 陆英杰
 * 2018/9/24 15:53
 */
@Service
public class InsertService {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    InsertDogJpa dogDao;

    @Autowired
    InsertOwnerJpa ownerDao;

    //使用jpa提供的方法插入
    public void insert1(){
        Dog dog=new Dog("111");
        Owner owner=new Owner("aaa");

        //设置关系
        owner.getDogs().add(dog);
        dog.setOwner(owner);

        ownerDao.save(owner);
    }

    //使用entityManager插入数据
    public void insert2(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Dog dog=new Dog("111");
        Owner owner=new Owner("aaa");

        //设置关系
        owner.getDogs().add(dog);
        dog.setOwner(owner);

        entityManager.persist(owner);//执行保存操作

        entityManager.getTransaction().commit();
    }


}

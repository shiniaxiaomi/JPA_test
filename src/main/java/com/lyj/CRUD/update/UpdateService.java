package com.lyj.CRUD.update;

import com.lyj.CRUD.insert.InsertDogJpa;
import com.lyj.CRUD.insert.InsertOwnerJpa;
import com.lyj.CRUD.model.Dog;
import com.lyj.CRUD.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/24 15:53
 */
@Service
@Transactional
public class UpdateService {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    UpdateDogJpa dogDao;
    @Autowired
    UpdateOwnerJpa ownerDao;

    //使用jpa提供的方法更新
    public void update1(){
        Optional<Owner> owner = ownerDao.findById(1);
        owner.get().setName("aaaaaaaaa");
        ownerDao.save(owner.get());
    }

    //使用@Query注解(HQL)更新
    public void update2(){
        ownerDao.updateNameById(1,"bbbbb");
    }

    //使用entityManager更新
    public void update3(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Owner owner = entityManager.find(Owner.class, 1);//先从数据库中查出来,将owner对象load到内存,变成pristine状态
        owner.setName("cccc");
        entityManager.merge(owner);//做更新操作

        entityManager.getTransaction().commit();
    }
}

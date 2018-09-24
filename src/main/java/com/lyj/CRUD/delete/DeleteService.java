package com.lyj.CRUD.delete;

import com.lyj.CRUD.insert.InsertDogJpa;
import com.lyj.CRUD.insert.InsertOwnerJpa;
import com.lyj.CRUD.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

/**
 * Created by 陆英杰
 * 2018/9/24 15:52
 */

@Service
@Transactional
public class DeleteService {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    DeleteDogJpa dogDao;
    @Autowired
    DeleteOwnerJpa ownerDao;

    //使用jpa提供的方法删除
    public void delete1(){
//        dogDao.deleteById(1);//删除dog
        ownerDao.deleteById(1);//删除owner
    }

    //使用@Query注解(HQL)删除
    public void delete2(){
        ownerDao.deleteByOwnerId(1);
//        ownerDao.deleteByNameLike("%a%");
    }

    //使用entityManager删除
    public void delete3(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Owner owner = entityManager.find(Owner.class, 1);//先从数据库中查出来,将owner对象load到内存,变成pristine状态
        entityManager.remove(owner);

        entityManager.getTransaction().commit();
    }

}

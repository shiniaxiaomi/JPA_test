package com.lyj.springboot.basic.service;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/20 9:50
 */

@Service
public class UserCrudService {
    @Autowired
    UserCrudRepository crudRepository;


    @Transactional
    public void savaUsers(List<User> users){
        crudRepository.saveAll(users);
    }

    public Optional<User> findById(int id){
        Optional<User> users = crudRepository.findById(id);
        return users;
    }

    public void deleteById(int id){
        crudRepository.deleteById(id);
    }

}

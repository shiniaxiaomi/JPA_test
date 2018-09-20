package com.lyj.springboot.basic.repositories;

import com.lyj.springboot.basic.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lyj80 on 2018/9/20.
 */

/**
 * 继承一个CrudRepository接口,就拥有了基本的增删改查的功能
 */
public interface UserCrudRepository extends CrudRepository<User,Integer> {


}

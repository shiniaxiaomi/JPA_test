package com.lyj.springboot.basic.repositories;

/**
 * Created by lyj80 on 2018/9/20.
 */

import com.lyj.springboot.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 带条件查询的分页
 */

public interface UserJpaSpecificationExecutor extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
}

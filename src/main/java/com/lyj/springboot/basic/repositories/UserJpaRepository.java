package com.lyj.springboot.basic.repositories;

import com.lyj.springboot.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 陆英杰
 * 2018/9/20 10:34
 */
public interface UserJpaRepository extends JpaRepository<User,Integer> {
}

package com.lyj.springboot.mapping.manyToOneDouble;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyj80 on 2018/9/22.
 */

public interface UserDao extends JpaRepository<User,Integer> {
}

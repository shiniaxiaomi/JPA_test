package com.lyj.springboot.mapping.repositories;

import com.lyj.springboot.mapping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 陆英杰
 * 2018/9/20 10:34
 */
public interface OrderDao extends JpaRepository<Order,Integer> {

    List<Order> findOrOrderByOrderNameLike(String name);

}

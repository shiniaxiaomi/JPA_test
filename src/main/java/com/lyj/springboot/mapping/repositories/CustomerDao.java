package com.lyj.springboot.mapping.repositories;

import com.lyj.springboot.mapping.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 陆英杰
 * 2018/9/20 10:34
 */
public interface CustomerDao extends JpaRepository<Customer,Integer> {
}

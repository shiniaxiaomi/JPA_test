package com.lyj.springboot.mapping.manyToMany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by lyj80 on 2018/9/22.
 */
public interface TaskDao2 extends JpaRepository<Task2,Integer> ,JpaSpecificationExecutor<Task2> {

    List<Task2> findByTaskNameLike(String name);
}

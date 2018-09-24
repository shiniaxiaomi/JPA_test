package com.lyj.springboot.mapping.oneToOne;

import com.lyj.springboot.mapping.manyToOneDouble.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyj80 on 2018/9/22.
 */
public interface TaskDao1 extends JpaRepository<Task1,Integer> {
}

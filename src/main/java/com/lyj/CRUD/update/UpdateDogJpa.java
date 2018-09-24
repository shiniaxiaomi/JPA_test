package com.lyj.CRUD.update;

import com.lyj.CRUD.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface UpdateDogJpa extends JpaRepository<Dog,Integer>{
}

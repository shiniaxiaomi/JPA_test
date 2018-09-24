package com.lyj.CRUD.delete;

import com.lyj.CRUD.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface DeleteDogJpa extends JpaRepository<Dog,Integer>{
}

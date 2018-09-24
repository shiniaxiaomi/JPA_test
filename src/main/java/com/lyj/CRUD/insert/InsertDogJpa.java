package com.lyj.CRUD.insert;

import com.lyj.CRUD.model.Dog;
import com.lyj.CRUD.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface InsertDogJpa extends JpaRepository<Dog,Integer>{
}

package com.lyj.CRUD.update;

import com.lyj.CRUD.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface UpdateOwnerJpa extends JpaRepository<Owner,Integer>{

    //使用@Query注解进行更新操作时,一定要加上@Modifying注解
    @Modifying
    @Query("update Owner o set o.name=:name where o.id=:id")
    public void updateNameById(@Param("id") Integer id,@Param("name") String name);
}

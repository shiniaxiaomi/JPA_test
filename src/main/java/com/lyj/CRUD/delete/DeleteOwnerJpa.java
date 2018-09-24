package com.lyj.CRUD.delete;

import com.lyj.CRUD.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface DeleteOwnerJpa extends JpaRepository<Owner,Integer>{


    @Modifying
    @Query(value = "delete from Owner o where o.id=:id")
    void deleteByOwnerId(@Param("id") Integer id);

    @Modifying
    @Query(value = "delete from Owner o where o.name like :name")
    void deleteByNameLike(@Param("name") String name);

}

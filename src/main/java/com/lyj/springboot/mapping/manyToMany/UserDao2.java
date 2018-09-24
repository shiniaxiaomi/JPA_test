package com.lyj.springboot.mapping.manyToMany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by lyj80 on 2018/9/22.
 */
public interface UserDao2 extends JpaRepository<User2,Integer>,JpaSpecificationExecutor<User2> {

    User2 findByIdAndNameLike(Integer id,String name);


    //在进行一对多查查询的时候,可以对多的一方进行条件设置
    @Query("select u from Task2 t join t.users u where t.id=:id and u.name like %:name%")
    List<User2> findByIdAndUserNameLike(@Param("id") Integer id,@Param("name") String name);

}

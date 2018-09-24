package com.lyj.CRUD.select;

import com.lyj.CRUD.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

/**
 * Created by lyj80 on 2018/9/24.
 */
public interface SelectOwnerJpa extends JpaRepository<Owner,Integer>,JpaSpecificationExecutor<Owner> {

    public List<Owner> findByNameLike(String name);

    //查询全部字段
    @Query("select o from Owner o where o.name like :name")
    public List<Owner> selectAllByNameLike(@Param("name") String name);

    //查询单个字段
    @Query("select o.name from Owner o where o.name like :name")
    public List selectAllByNameLike_1(@Param("name") String name);

    //查询部分字段
    @Query("select o.name,o.id from Owner o where o.name like :name")
    public List<Object[]> selectAllByNameLike_2(@Param("name") String name);

    //关联查询,返回一个自定义对象的结果集
    @Query("select new com.lyj.CRUD.select.DTO(o.id,o.name,d.name) from Owner o join o.dogs d where o.name like :name and d.name like :dogName")
    public List<DTO> selectOwnerAndDogName(@Param("name") String name,@Param("dogName") String dogName);

    //使用原生的sql进行查询(查询全部字段)
    @Query(value = "select * from owner o where o.name like :name",nativeQuery = true)
    public List<Owner> selectSQL(@Param("name") String name);

    //使用原生的sql进行查询(查询部分字段)
    @Query(value = "select o.name from owner o where o.name like :name",nativeQuery = true)
    public List<Object> selectSQL_1(@Param("name") String name);

}

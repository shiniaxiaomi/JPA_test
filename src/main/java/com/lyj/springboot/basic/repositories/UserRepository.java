package com.lyj.springboot.basic.repositories;

import com.lyj.springboot.basic.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 陆英杰
 * 2018/9/19 22:52
 */


/**
 * 1. repository是一个空接口,即是一个标记接口
 * 2. 若我们定义的接口继承了Repository,则该接口会被IOC容器识别为一个Repository bean
 *          纳入到IOC 容器中,进而可以在改接口中定义满足一定规范的方法
 * 3. 实际上,也可以通过@RepositoryDefinition 注解来代替继承Repository接口
 */

/**
 * 在Repository 子接口中声明方法
 *  1. 不是随便声明的,二需要符合一定的规范
 *  2.查询方法以find|read|get开头
 *  3.设计条件查询时,条件的属性用条件关键字连接
 *  4.要注意的是:条件属性以首字母大写
 *  5.支持属性的级联查询.若当前类有符合条件的属性,则优先使用,而不是用级联属性
 *      若需要使用级联属性,则属性之间使用_进行连接
 */
//@RepositoryDefinition(domainClass = User.class,idClass = Integer.class)
public interface UserRepository extends Repository<User,Integer> {

    //根据userName来回去对应的User
    User getByUserName(String name);

    //where id=?
    User getUserById(Integer id);

    //where userName like ?% and id<?
    List<User> getByUserNameStartingWithAndIdLessThan(String name,Integer id);

    //where id in(?,?,?) or password=?
    List<User> getByIdInOrPasswordEquals(List<Integer> ids,String password);

    //查询id 值最大的那个User
    //使用@Query 注解可以自定义JPQL语句以实现更灵活的查询
    @Query(value = "select COUNT(*) from db_user where id=1",nativeQuery = true)
    Integer getConutUserById();

    //直接使用原生sql语句进行查询(使用原生sql要加上nativeQuery = true)
    @Query(value = "select * from db_user u where id=1",nativeQuery = true)
    List<User> getMaxIdUser();

    //为@Query注解传递参数的方式1: 使用占位符(要求参数顺序要一致)
    @Query(value = "select * from db_user u where id=?1",nativeQuery = true)
    List<User> getMaxIdUser2(Integer id);

    //为@Query注解传递参数的方式2: 使用名称占位符(要求参数名称一致)
    @Query(value = "select id,password,user_name from db_user u where id=:id",nativeQuery = true)
    List<User> getMaxIdUser3(@Param("id") Integer id);

    //可以通过自定义的JPQL完成update和delete操作哦;注意:JPQL不支持使用insert
    //在@Query注解中编写JPQL语句,必须使用@Modifying进行修饰,以通知springdata,这是一个update或者delete操作
    //update或者delete操作需要使用事务,此时需要加上@Transactional注解
    //默认去情况下,springdata的每个方法上都有事务,但是都是一个只读事务,他们不能完成修改和删除操作
    @Transactional
    @Modifying
    @Query(value = "update db_user u set u.user_name=?1 where id=?2",nativeQuery = true)
    void updateUser(String name,Integer id);


    @Transactional
    @Modifying
    @Query(value = "DELETE from db_user where id=?1",nativeQuery = true)
    void deleteUser(Integer id);
}

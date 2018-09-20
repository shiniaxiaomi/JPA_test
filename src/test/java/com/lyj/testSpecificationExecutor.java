package com.lyj;

import com.lyj.springboot.basic.entity.User;
import com.lyj.springboot.basic.repositories.UserJpaSpecificationExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;

/**
 * Created by 陆英杰
 * 2018/9/20 10:50
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class testSpecificationExecutor {

    @Autowired
    UserJpaSpecificationExecutor specificationExecutor;

    /**
     * 目标: 实行带查询条件的分页
     * 调用 JpaSpecificationExecutor 的Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);
     *      Specification<T>: 封装了jpa Criteria 查询的查询条件
     *      Pageable: 封装了请求分页的信息
     */
    @Test
    public void test1(){
        Sort orders = Sort.by(Sort.Order.desc("id"), Sort.Order.asc("password"));//封装了排序的信息,先按id降序,再按password升序排列
        PageRequest pageRequest = PageRequest.of(1, 4, orders);//封装了分页的信息,页数,个数,和排序信息

        /**
         * 通常使用specification的匿名内部类
         * @param root: 代表查询的实体类
         * @param criteriaQuery: 可以从中得到Root对象,即告知JPA Criteria 要查询哪一个实体类,还可以添加查询条件
         * @param criteriaBuilder : 用于创建Criteria 相关对象的工厂,当然可以从中会回去到Predicate对象
         * @return
         */
        Specification<User> specification=new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("id");
                Predicate predicate= criteriaBuilder.gt(path,5);
                return predicate;
            }
        };

        Page<User> page = specificationExecutor.findAll(specification,pageRequest);//使用带条件的分页查询

        System.out.println("总记录数:"+page.getTotalElements());
        System.out.println("当前第几页:"+page.getNumber());
        System.out.println("总页数:"+page.getTotalPages());
        System.out.println("当前页面的List:"+page.getContent());
        System.out.println("当前页面的记录数"+page.getNumberOfElements());


    }
}

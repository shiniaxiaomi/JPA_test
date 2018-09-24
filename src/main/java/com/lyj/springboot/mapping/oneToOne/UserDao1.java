package com.lyj.springboot.mapping.oneToOne;

import com.lyj.springboot.mapping.manyToOneDouble.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by lyj80 on 2018/9/22.
 */
public interface UserDao1 extends JpaRepository<User1,Integer> {
    //自定义原生sql,在删除和更新操作时要加上@Modifying注解
    @Modifying
    @Query(value = "DELETE FROM user1 WHERE id=:id",nativeQuery=true)
    void deleteUser1ById(@Param("id") Integer id);
}

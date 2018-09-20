package com.lyj.springboot.basic.repositories;

import com.lyj.springboot.basic.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lyj80 on 2018/9/20.
 */
public interface UserPagingRepository extends PagingAndSortingRepository<User,Integer> {
}

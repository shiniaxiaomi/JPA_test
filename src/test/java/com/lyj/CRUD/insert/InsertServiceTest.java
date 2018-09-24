package com.lyj.CRUD.insert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lyj80 on 2018/9/24.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertServiceTest {

    @Autowired
    InsertService service;

    @Test
    public void insert1() throws Exception {
        service.insert1();
    }

    @Test
    public void insert2() throws Exception {
        service.insert2();
    }

}
package com.lyj.CRUD.delete;

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
public class DeleteServiceTest {

    @Autowired
    DeleteService service;

    @Test
    public void delete1() throws Exception {
        service.delete1();
    }
    @Test
    public void delete2() throws Exception {
        service.delete2();
    }
    @Test
    public void delete3() throws Exception {
        service.delete3();
    }

}
package com.lyj.CRUD.update;

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
public class UpdateServiceTest {

    @Autowired
    UpdateService service;

    @Test
    public void update1() throws Exception {
        service.update1();
    }

    @Test
    public void update2() throws Exception {
        service.update2();
    }

    @Test
    public void update3() throws Exception {
        service.update3();
    }

}
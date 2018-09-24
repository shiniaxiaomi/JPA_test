package com.lyj.CRUD.select;

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
public class SelectServiceTest {

    @Autowired
    SelectService service;

    @Test
    public void select1() throws Exception {
        service.select1();
    }
    @Test
    public void select1_1() throws Exception {
        service.select1_1();
    }
    @Test
    public void select1_2() throws Exception {
        service.select1_2();
    }
    @Test
    public void select1_3() throws Exception {
        service.select1_3();
    }
    @Test
    public void select1_4() throws Exception {
        service.select1_4();
    }

    @Test
    public void select2() throws Exception {
        service.select2();
    }
    @Test
    public void select2_1() throws Exception {
        service.select2_1();
    }
    @Test
    public void select2_2() throws Exception {
        service.select2_2();
    }
    @Test
    public void select2_3() throws Exception {
        service.select2_3();
    }
    @Test
    public void select2_4() throws Exception {
        service.select2_4();
    }
    @Test
    public void select2_5() throws Exception {
        service.select2_5();
    }

    @Test
    public void select3() throws Exception {
        service.select3();
    }
    @Test
    public void select3_1() throws Exception {
        service.select3_1();
    }
    @Test
    public void select3_2() throws Exception {
        service.select3_2();
    }
    @Test
    public void select3_3() throws Exception {
        service.select3_3();
    }
    @Test
    public void select4() throws Exception {
        service.select4();
    }
    @Test
    public void select4_1() throws Exception {
        service.select4_1();
    }

}
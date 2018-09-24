package com.lyj.spring.manyToMany;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by lyj80 on 2018/9/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void before(){
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();//开始事务
    }
    @After
    public void after(){
        entityManager.getTransaction().commit();//提交事务
        entityManager.close();
        entityManagerFactory.close();
    }

    //保存学生和老师,并保存学生和老师的关系
    @Test
    public void save(){
        Student student=new Student("aaa111222333");
        Teacher teacher=new Teacher("bbb111222333");
        student.addTeacher(teacher);//建立学生和老师的关系

        entityManager.persist(student);
    }

    //删除学生与老师的关系
    @Test
    public void remove(){

        Student student = entityManager.find(Student.class, 9);
//        Teacher teacher = entityManager.find(Teacher.class, 1);//从数据库中查找老师
        Teacher teacher = new Teacher(6);//直接new一个老师
        student.removeTeacher(teacher);//删除学生和老师的关系

        entityManager.merge(student);
    }

    //删除老师,并删除学生与老师的关系
    @Test
    public void removeTeacher(){

        Student student = entityManager.find(Student.class, 9);
        Teacher teacher = entityManager.getReference(Teacher.class, 6);//从数据库中查找老师(使用getReference可以提高效率)
        student.removeTeacher(teacher);

        entityManager.remove(teacher);//在进行删除老师的时候,会把学生和老师的关系删除(并不会删除学生,如果老师设置了级联删除,则会删除学生)
    }

    //删除学生,并删除学生与老师的关系
    @Test
    public void removeStudent(){

        Student student = entityManager.find(Student.class, 10);
        Teacher teacher = entityManager.getReference(Teacher.class, 7);//从数据库中查找老师(使用getReference可以提高效率)
        student.removeTeacher(teacher);

        entityManager.remove(student);

    }

    //不设置级联删除: 删除学生,老师,还有学生和老师的关系
    @Test
    public void removeStudentAndTeacher2(){

        Student student = entityManager.find(Student.class, 10);
        Teacher teacher = entityManager.getReference(Teacher.class, 7);//从数据库中查找老师(使用getReference可以提高效率)
        student.removeTeacher(teacher);

        entityManager.remove(student);
        entityManager.remove(teacher);

    }

    //级联删除: 删除学生,老师,还有学生和老师的关系
    @Test
    public void removeStudentAndTeacher(){

        Student student = entityManager.find(Student.class, 1);
        Teacher teacher = entityManager.getReference(Teacher.class, 1);//从数据库中查找老师(使用getReference可以提高效率)
        student.removeTeacher(teacher);

        //如果没有定义级联删除,那么只会删除老师和关系表中的数据
        //(如果老师定义了级联删除,那么删除老师的时候就会触发这个级联删除,然后会继而删除学生)
        entityManager.remove(teacher);
    }


}
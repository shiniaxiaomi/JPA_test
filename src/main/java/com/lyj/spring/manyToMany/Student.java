package com.lyj.spring.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/21 19:32
 */

@Entity
public class Student {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String name;

    /**
     * @ManyToMany: 定义多对多关联关系
     *
     * @JoinTable: 使用这个注解可以定义关联表的名称
     *      1.inverseJoinColumns: 定义关系被维护端外键的名称
     *      2.joinColumns: 定义关系维护端外键的名称(就是外键的所在端)
     *                     标注在哪个字段上,就写哪个字段的id
     */
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(name = "student_teacher",inverseJoinColumns = @JoinColumn(name = "teacher_id"),
                joinColumns = @JoinColumn(name = "student_id"))
    private Set<Teacher> teachers=new HashSet<>();

    public void addTeacher(Teacher teacher){
        this.teachers.add(teacher);
    }


    public void removeTeacher(Teacher teacher){
        if(this.teachers.contains(teacher)){
            this.teachers.remove(teacher);
        }
    }

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Teacher> getSet() {
        return teachers;
    }

    public void setSet(Set<Teacher> set) {
        this.teachers = set;
    }
}

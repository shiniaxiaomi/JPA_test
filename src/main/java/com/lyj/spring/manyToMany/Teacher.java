package com.lyj.spring.manyToMany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 陆英杰
 * 2018/9/21 19:32
 */

@Entity
public class Teacher {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;

    //定义Teacher是关系被维护端
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST},mappedBy = "teachers")
    private Set<Student> students=new HashSet<>();

    //重写equals方法,以便于集合的contains()方法的判断
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;

        Teacher teacher = (Teacher) o;

        return id != null ? id.equals(teacher.id) : teacher.id == null;
    }

    //重写hashCode方法,以便于集合的contains()方法的判断
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public Teacher() {
    }

     public Teacher(Integer id){
        this.id=id;
     }

    public Teacher(String name) {
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

    public Set<Student> getSet() {
        return students;
    }

    public void setSet(Set<Student> set) {
        this.students = set;
    }
}

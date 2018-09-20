package com.lyj.spring.ontToOneDouble;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/20 23:53
 */

@Entity
public class People {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;

    @OneToOne(optional = false,cascade = {CascadeType.ALL})
    private IDCard idCard;

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
        this.idCard = idCard;
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

    public People(String name, IDCard idCard) {
        this.name = name;
        this.idCard = idCard;
    }

    public People() {
    }
}

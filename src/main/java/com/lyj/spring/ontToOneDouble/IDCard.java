package com.lyj.spring.ontToOneDouble;

import com.lyj.spring.Person;

import javax.persistence.*;

/**
 * Created by 陆英杰
 * 2018/9/20 23:53
 */

@Entity
public class IDCard {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String cardNo;

    /**
     * @OneToOne: 一对一双向关系维护
     *      @JoinColumn: 可以指定外键的字段名称
     *      mappedBy: (很重要)
     *          1: 可以这么理解,即mappedBy在哪个类,则这个类的主键就和其他表的外键相关联(然后外键就会在其他表中)------[很容易理解]
     *          2. 如果mappedBy = "idCard",则idCard所在的类所映射的表中有外键,关联的是IdCard类中的主键(id)----[很重要]
     *      cascade: 设置级联属性
     *          1.CascadeType.PERSIST: 保存的时候级联(当entityManager调用persist()方法时)
     *          2.CascadeType.MERGE: 当从游离态变成持久态的时候级联(当entityManager调用merge()方法时)
     *          3.CascadeType.REFRESH: 刷新的时候级联(当entityManager调用refresh()方法时)
     *          4.CascadeType.REMOVE: 删除的时候级联(当entityManager调用remove()方法时)
     *          5.CascadeType.DETACH: 级联脱管/游离操作(当entityManager调用detach()方法时)
     *          6.CascadeType.ALL: 包含所有级联属性
     *      optional: 设置可选项(为true表示该外键可以为null,为false则不能为null)
     */
    @OneToOne(mappedBy = "idCard",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private People people;

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public IDCard(String cardNo) {
        this.cardNo = cardNo;
    }

    public IDCard() {
    }
}

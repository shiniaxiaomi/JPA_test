package com.lyj.CRUD.select;

import com.lyj.CRUD.model.Owner;
import com.lyj.CRUD.update.UpdateDogJpa;
import com.lyj.CRUD.update.UpdateOwnerJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GenerationType;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by 陆英杰
 * 2018/9/24 15:53
 */
@Service
@Transactional
public class SelectService {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    SelectDogJpa dogDao;
    @Autowired
    SelectOwnerJpa ownerDao;

    /**
     * 使用jpa提供的方法
     */
    //通过id查询
    public void select1(){
        Optional<Owner> owner = ownerDao.findById(1);
        System.out.println(owner);
    }
    //通过在接口中编写方法实现查询
    public void select1_1(){
        List<Owner> owners = ownerDao.findByNameLike("%c%");
        for(Owner owner1:owners){System.out.println(owner1);}
    }
    //通过Example查询的默认查询
    public void select1_2(){
        List<Owner> owners = ownerDao.findAll(Example.of(new Owner("33")));//默认忽略null值的属性
        for(Owner owner1:owners){System.out.println(owner1);}
    }
    //通过Example查询的构建复杂查询
    public void select1_3(){
        Owner owner=new Owner();//创建一个例子对象
        owner.setName("c");

        ExampleMatcher matcher = ExampleMatcher.matching(); //构建条件管理对象
        matcher.withIgnorePaths("id");//忽略id属性(即不查询id字段)
        matcher.withMatcher("name", match -> match.contains());

        ownerDao.findAll(Example.of(owner,matcher));
    }
    //通过Specification进行构建更加复杂的查询,首先要先实现JpaSpecificationExecutor接口
    public void select1_4(){
        /**
         * jpa拼接后的sql
         * SELECT
         o.id,o.name
         FROM OWNER o
         INNER JOIN dog d ON o.id = d.owner_id
         WHERE (o.id = 1 OR o.id = 2 OR o.name LIKE ?) and d.id = 1
         */
        //创建一个匿名内部类,来封装复杂查询条件
        Specification<Owner> specification=new Specification<Owner>() {
            @Override
            public Predicate toPredicate(Root<Owner> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate id_1 = criteriaBuilder.equal(root.get("id"), 1);//创建条件1,查询id
                Predicate id_2 = criteriaBuilder.equal(root.get("id"), 2);//创建条件2,查询id
                Predicate id = criteriaBuilder.or(id_1,id_2);//将条件1和条件2用or连接,封装成一个新的条件

                Predicate name = criteriaBuilder.like(root.get("name"), "%3%");//创建新的条件,查询name

                Predicate idAndName = criteriaBuilder.or(id, name);//id总条件和name总条件再使用 or连接

                //关联user表
                Join<Object, Object> join = root.join("dogs");//关联表(Owner对象中的属性的字段名),返回一个Join对象
                Predicate dogId = criteriaBuilder.equal(join.get("id"), 1);//通过join对象添加关联表的条件

                criteriaQuery.where(idAndName, dogId);//可以传入多个参数,默认将多个参数使用and连接
                return null;
            }
        };

        List<Owner> list = ownerDao.findAll(specification);//使用创建的查询条件进行搜索

        for(Owner o:list){System.out.println(o);}
    }

    /**
     * 使用@Query注解(HQL)
     */
    //使用@Query注解(HQL) 查询所有字段
    public void select2(){
        List<Owner> owners = ownerDao.selectAllByNameLike("%c%");
        for(Owner owner1:owners){System.out.println(owner1);}
    }
    //使用@Query注解(HQL) 查询单个字段
    public void select2_1(){
        List owners = ownerDao.selectAllByNameLike_1("%c%");
        for(Object o:owners){
            System.out.println(o);
        }
    }
    //使用@Query注解(HQL) 查询部分字段
    public void select2_2(){
        List<Object[]> list = ownerDao.selectAllByNameLike_2("%c%");
        for(Object[] objects:list){
            for(Object o:objects){
                System.out.print(o+",");//打印的数据是按照sql查询字段的数据确定的
            }
            System.out.println();
        }
    }
    //使用@Query注解(HQL) 自己封装结果集(自己封住对象)
    public void select2_3(){
        List<DTO> list = ownerDao.selectOwnerAndDogName("%c%", "%11%");
        for(DTO dto:list){System.out.println(dto);}
    }
    //使用@Query注解(HQL) 原生的sql进行查询全部字段
    public void select2_4(){
        List<Owner> list = ownerDao.selectSQL("%c%");
        for(Owner owner:list){System.out.println(owner);}
    }
    //使用@Query注解(HQL) 原生的sql进行查询部分字段
    public void select2_5(){
        List<Object> list = ownerDao.selectSQL_1("%c%");
        for(Object o:list){System.out.println(o);}
    }

    /**
     * 使用entityManager查询
     */
    //使用entityManager查询,根据id查询
    public void select3(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Owner owner = entityManager.find(Owner.class, 1);
        System.out.println(owner);

        entityManager.getTransaction().commit();
    }
    //使用entityManager查询, 使用原生的sql进行查询
    public void select3_1(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        //第一种查询(强转成对象)
//        Query query = entityManager.createNativeQuery("SELECT * from owner",Owner.class);//可以传入查询结果集的对象,方便之后强转
//        List<Owner> list = query.getResultList();
//        for(Owner owner:list){
//            System.out.println(owner);
//        }
        //第二种查询(遍历Object)
        Query query = entityManager.createNativeQuery("SELECT * from owner");
        List list = query.getResultList();
        for(int i=0;i<list.size();i++){
            Object[] objects = (Object[]) list.get(i);
            for(Object obj:objects){
                System.out.print(obj+",");
            }
            System.out.println();
        }

        entityManager.getTransaction().commit();
    }
    //使用entityManager查询, 使用Query查询(HQL语句)
    public void select3_2(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select o from Owner o where o.id=:id");
        query.setParameter("id", 1);
        Owner result = (Owner) query.getSingleResult();
        System.out.println(result);

        entityManager.getTransaction().commit();
    }
    //使用entityManager查询, 使用QBC语句查询(可以进行复杂的动态条件查询)
    public void select3_3(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();//获取criteriaBuilder
        CriteriaQuery<Owner> query = criteriaBuilder.createQuery(Owner.class);//创建一个CriteriaQuery

        Root<Owner> ownerRoot = query.from(Owner.class);//创建根路径Root
        query.select(ownerRoot);
        //criteriaBuilder是用来创建一些查询条件的,他可以将多个查询条件进行拼装
        Predicate id = criteriaBuilder.equal(ownerRoot.get("id"), 1);//添加查询条件1
        Predicate name = criteriaBuilder.like(ownerRoot.get("name"), "%cc%");//添加查询条件2

        Predicate idOrName = criteriaBuilder.or(id, name);//使用or()方法将条件1和条件2用or连接形成一个查询条件

        query.where(idOrName);//将条件放入where里面(即条件就会跟在where语句后面)

        List<Owner> list = entityManager.createQuery(query).getResultList();
        for(Owner owner:list){System.out.println(owner);}
        entityManager.getTransaction().commit();
    }

    /**
     * 分页查询
     */
    //分页查询,不排序
    public void select4(){
        Page<Owner> page = ownerDao.findAll(PageRequest.of(0, 4));//封装了分页的信息,页数,个数
        System.out.println(page.getContent());
    }
    //分页查询,排序
    public void select4_1(){
        Sort sort = Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name"));//封装了排序的信息,先按id降序,再按password升序排列
        Page<Owner> page = ownerDao.findAll(PageRequest.of(0, 4,sort));//封装了分页的信息,页数,个数
        System.out.println(page.getContent());
    }
}

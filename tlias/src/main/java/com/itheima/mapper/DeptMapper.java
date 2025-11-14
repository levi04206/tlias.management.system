package com.itheima.mapper;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper{


    /*查询所有部门数据*/

    /*字段名与属性名不一致的解决办法*/

//    方式一：借助@Result和@Results注解
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })

    //方式二：起别名
    @Select("select * from tlias.dept order by update_time desc")
    List<Dept> findAll();


    /*根据id删除部门*/

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /*添加部门*/

    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /*查询回显*/
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /*根据id修改部门*/

    @Update("update dept set name= #{name},update_time= #{updateTime} where id = #{id}")
    void update(Dept dept);
};

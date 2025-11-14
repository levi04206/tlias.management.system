package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*分页查询班级列表*/
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /*新增班级*/
    @Insert("insert into clazz (name, room, begin_date, end_date, master_id, subject, create_time, update_time) values" +
            "                 (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /*根据id删除部门*/
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /*根据id查询班级*/
    @Select("select c.* from clazz c where c.id = #{id};")
    Clazz selectById(Integer id);

    /*修改班级*/
    @Update("update clazz c set id = #{id},name = #{name},room = #{room},begin_date = #{beginDate}," +
            "end_date = #{endDate},master_id = #{masterId},subject = #{subject},create_time = #{createTime},update_time = #{updateTime} where id = #{id};")
    void update(Clazz clazz);

    /*查询所有列表*/
    @Select("select * from clazz")
    List<Clazz> findAll();
}

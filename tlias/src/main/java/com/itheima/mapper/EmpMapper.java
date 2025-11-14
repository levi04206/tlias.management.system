package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
    /*查询员工信息*/

//    // 获取查询总记录数  原始分页查询操作
//    @Select("select count(*) from emp;")
//    public long count();
//
//    //获取查询到的数据列表（分页展示）
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{startIndex},#{pageSize};")
//    public List<Emp> list(@RequestParam Integer startIndex, Integer pageSize);

    /*pageHelper方法进行分页查询
    * 注意事项：
    * 1、pagehelper执行的sql语句结尾不能加“;”
    * 2、pagehelper仅仅能对紧跟在其行后面的第一个查询语句进行分页处理*/
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    public List<Emp> list(EmpQueryParam empQueryParam);

    /*新增员工*/
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取到生成的主键(主键返回)
    @Insert("  insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "  values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /*根据id删除员工基本信息*/
    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    /*根据部门id统计员工数量*/
    @Select("select count(*) num from emp e where e.dept_id =  #{deptId};")
    Long countEmpData(Integer deptId);

    @MapKey("pos")
    /*查询员工职位列表报表*/
    //该集合的含义假如：职位：学工部：2  教研部：3，财务部：4，职位字段名为pos,人数字段名为num,这两个字段名就是Map中的键，而其对应的值就是map中的值
    //第一个map的值{pos:学工部，num:2}  {pos:教研部，num:3} {pos:财务部,num:4}
    List<Map<String,Object>> countEmpJobData();

    /*查询员工性别分布*/
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /*查询学员班级分布*/
    @MapKey("clz")
    List<Map<String, Object>> countStudentData();

    /*学员最高学历统计*/
    @MapKey("deg")
    List<Map<String,Object>> countStudentDegreeData();

    @Select("select * from emp")
    /*查询所有员工*/
    List<Emp> findAll();

    /*用户登录*/
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp getByUserNameAndPassword(Emp emp);
}

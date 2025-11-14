package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


//    @Override
//    /*原始分页查询*/
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //获取总记录数
//        long total = empMapper.count();
//        //获取数据列表
//        Integer startIndex = (page - 1)*pageSize;
//        List<Emp> rows= empMapper.list(startIndex, pageSize);
//        return new PageResult<Emp>(total,rows);
//    }

    /*pageHelper分页查询*/
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1、设置分页参数
//        PageHelper.startPage(page,pageSize);
//        //2、执行查询
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
//        //3、解析查询结果并封装
//        Page<Emp> list = (Page<Emp>) empList;
//        return new PageResult<Emp>(list.getTotal(),list.getResult());
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1、设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2、执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3、解析查询结果并封装
        Page<Emp> list = (Page<Emp>) empList;
        return new PageResult<Emp>(list.getTotal(),list.getResult());
    }

    /*新增员工*/
    @Transactional(rollbackFor = {Exception.class})//事务管理，默认出现运行时异常RuntimeException才会回滚。
    @Override
    public void insert(Emp emp){
        try {
            //1、保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
//            int a = 1/0;
            //2、保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(expr ->
                        expr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);//此时批量保存员工工作经历
            }
        } finally {
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工" + emp);
            //无论新增员工成功与失败，都要记录日志
            empLogService.insertLog(empLog);
        }

    }

    /*根据id删除员工*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> ids) {
        //根据id删除员工基本信息
        empMapper.deleteByIds(ids);

        //根据id删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /*根据id修改信息*/
    @Override
    public void updateById(Emp emp) {
        //根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据id修改员工工作经历
        //1、先删除工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2、再添加工作经历
        //前端发起添加请求，说明此时的工作经历不为0
        List<EmpExpr> exprList = emp.getExprList();
        if(exprList != null && !exprList.isEmpty()){
            exprList.forEach(expr -> expr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);//批量保存员工工作经历（主要看前端是否添加）
        }

    }

    /*查询所有员工*/
    @Override
    public List<Emp> findAll() {
        List<Emp> emp = empMapper.findAll();
        return emp;
    }

    /*登录认证*/
    @Override
    public LoginInfo log(Emp emp) {
        //1、根据用户名和密码查询员工信息
       Emp e =  empMapper.getByUserNameAndPassword(emp);
       //2、判断员工信息是否为空
        //3、返回员工信息
        if(e != null){
            Map<String, Object> dataMap = new HashMap<>();//自定义数据
            dataMap.put("id",e.getId());
            dataMap.put("username",e.getUsername());
            String jwt = JwtUtils.generateJwt(dataMap);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }

        return null;
    }
}

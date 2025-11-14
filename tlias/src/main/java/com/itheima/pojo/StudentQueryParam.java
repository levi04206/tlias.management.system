package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name;//学员姓名
    private Integer degree;//学员最高学历
    private Integer clazzId;//学院所属班级
    private Integer page = 1;//当前页码数
    private Integer pageSize = 10;//分页查询每页记录数
}

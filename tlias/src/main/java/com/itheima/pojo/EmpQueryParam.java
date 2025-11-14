package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1;//页码数
    private Integer pageSize = 10;//每页展示数据个数
    private String name;//员工名称
    private Integer gender;//员工性别
    @DateTimeFormat(pattern = "yyyy-MM-ss")
    private LocalDate begin;//起始时间
    @DateTimeFormat(pattern = "yyyy-MM-ss")
    private LocalDate end;//结束时间

}

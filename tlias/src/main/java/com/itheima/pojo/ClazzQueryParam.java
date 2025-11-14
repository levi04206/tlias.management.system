package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*班级查询列表参数*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name;//班级名称
    @DateTimeFormat(pattern = "yyyy-MM-ss")
    private LocalDate begin;//范围匹配的开始时间（结课时间）
    @DateTimeFormat(pattern = "yyyy-MM-ss")
    private LocalDate end;//范围匹配的结束时间（结课时间）
    private Integer page = 1;//分页查询的页码
    private Integer pageSize = 10;//分页擦汗寻的每页记录数
}

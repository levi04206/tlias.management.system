package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLogParam {
    private Integer page = 1;//页码数
    private Integer pageSize = 10;//每页展示数据个数
}

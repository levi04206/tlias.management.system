package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzOption {
    private List clazzList;//班级名称列表
    private List dataList;//班级所对应学员数据列表
}

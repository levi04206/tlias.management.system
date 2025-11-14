package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.StudentDegreeOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    ClazzOption getStudentData();

    List<StudentDegreeOption> getStudentDegreeData();
}

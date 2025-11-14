package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

//    /*文件上传功能
//    * 将文件保存在本地磁盘中*/
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
//        log.info("接收参数:{},{},{}",name,age,file);
//        String originalFilename = file.getOriginalFilename();//获取原始文件名
//
//        //将原始文件名按照最后一个‘.’进行分割并取其后缀
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//
//        //通过UUID随机生成文件名加上原文件的后缀构成新文件名称
//        String newFileNmae = UUID.randomUUID().toString() + extension;
//        //保存文件到指定文件夹中
//        file.transferTo(new File("D:/images/" + newFileNmae));
//        return Result.success();
//    }
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(url);
    }
}

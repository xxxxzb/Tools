package com.live.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 接口
 */
@Controller
public class CrowdfundingController {

    @Value("${file.path}")
    private String path;

    //初始化页面
    @RequestMapping("/")
    public String init(){
        return "test";
    }

    //处理文件上传(1、@RequestParam("file")MultipartFile file拿到上传文件)
    //           2、FileOutputStream
    @ResponseBody
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public String uplaod(@RequestParam("file")MultipartFile file){
        //定义文件名
        String fileName = new Date().getTime()+"_"+file.getOriginalFilename();

        try {
            //创建输出流
            FileOutputStream outputStream = new FileOutputStream(path + fileName);

            outputStream.write(file.getBytes());

            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "test";
    }
	
	/*
		html：
		
		<form action="/upload" method="post" enctype="multipart/form-data">
			单个文件上传
			<input type="file" name="file">
			<button type="submit">提交</button>
		</form>
	*/
	
}

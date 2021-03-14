package com.springmvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadDemo {
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
//	@RequestMapping("/testUpload")
//	public void testUpload(MultipartFile myFile){
//		System.out.println(myFile.getOriginalFilename());
//	}
	
	@RequestMapping("/testUpload")
	public void testUpload(@RequestParam("myFile") MultipartFile abc, HttpServletResponse response) throws IOException{
		String originalFilename = abc.getOriginalFilename();
//		System.out.println(originalFilename);
		String suffix = originalFilename.substring(originalFilename.indexOf("."));
		String fileStr = "D:\\"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+suffix;
		abc.transferTo(new File(fileStr)); // 上传到指定盘符
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");        
        PrintWriter writer = response.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
        .append("<html>\r\n")
        .append("    <head>\r\n")
        .append("        <title>ModelAttribute Demo</title>\r\n")
        .append("    </head>\r\n")
        .append("    <body>\r\n")
        .append("        ").append(originalFilename).append(" 上传成功！上传路径：").append(fileStr).append("<br/>\r\n")
        .append("    </body>\r\n")
        .append("</html>\r\n");
	}
	
}

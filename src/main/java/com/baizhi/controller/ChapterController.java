package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Chapter chapter, MultipartFile file) {

        Map map = new HashMap();
        try {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "d:\\lun\\";
            System.out.println(UUID.randomUUID().toString());
            String filenewName = UUID.randomUUID() + suffixName;
            File desc = new File(filePath + filenewName);
            file.transferTo(desc);
            chapter.setFilename(filenewName);
            chapter.setPublishdate(new Date());
            chapter.setSize(getPrintSize(file.getSize()));
            Long duration = AudioUtil.getDuration(desc);
            chapter.setDuration(String.valueOf(duration));

            chapterService.insert(chapter);
            map.put("inserta", true);
        } catch (Exception e) {
            map.put("inserta", false);
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("download")
    @ResponseBody
    public void downLoad(int id, HttpServletResponse response) {
        System.out.println(id);
        //获取文件的路径
        Chapter chapter = chapterService.selectone(id);
        String filename = chapter.getFilename();
        String title = chapter.getTitle();
        String filePath1 = "d:\\lun\\";
        String filePath = filePath1 + filename;
        File file = new File(filePath);
        //修改下载时的名字
        String extension = FilenameUtils.getExtension(filename);
        String oldName = title + "." + extension;
        //下载
        //设置响应的编码
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        //设置响应类型
        response.setContentType("audio/mpeg");

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }


}

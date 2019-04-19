package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;


    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Banner banner, MultipartFile file) {
        System.out.println("111");
        Map map = new HashMap();
        try {

            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "D://lun//";
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            file.transferTo(dest);//保存文件
            banner.setImgpath(fileName);
            banner.setCreatdate(new Date());
            bannerService.insert(banner);
            map.put("insert", true);
        } catch (Exception e) {
            map.put("insert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("select")
    @ResponseBody
    public Map select(int page, int rows) {
        return bannerService.selectall(page, rows);
    }

    @RequestMapping("update")
    @ResponseBody
    public Map update(Banner b, MultipartFile file) {
        Map map = new HashMap();
        try {
            bannerService.update(b);
            map.put("update", true);
        } catch (Exception e) {
            map.put("update", false);
            e.printStackTrace();


        }
        return map;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Map delete(int id) {
        Map map = new HashMap();
        try {
            bannerService.deleteone(id);
            map.put("delete", true);
        } catch (Exception e) {
            map.put("delete", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("update1")
    @ResponseBody
    public Map update1(Banner b, MultipartFile file) {
        System.out.println(b);
        Map map = new HashMap();
        try {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "D://lun//";
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            file.transferTo(dest);
            b.setImgpath(fileName);
            b.setCreatdate(new Date());
            bannerService.update(b);
            map.put("update", true);
        } catch (Exception e) {
            map.put("update", false);
            e.printStackTrace();
        }
        return map;
    }
}

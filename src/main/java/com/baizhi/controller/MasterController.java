package com.baizhi.controller;

import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("master")
public class MasterController {
    @Autowired
    private MasterService masterService;

    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Master master, MultipartFile file) {
        Map map = new HashMap();
        try {
            String filename = file.getOriginalFilename();
            String filePath = "d:\\lun\\";
            String fileNewName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
            File file1 = new File(filePath + fileNewName);
            file.transferTo(file1);
            master.setMpic(fileNewName);
            master.setMstatus(1);
            masterService.insert(master);
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
        return masterService.selectall(page, rows);
    }

    @RequestMapping("selectm")
    public String selectm(HttpSession session) {
        List<Master> list = masterService.select();
        session.setAttribute("list", list);
        return "redirect:/user.jsp";
    }
}

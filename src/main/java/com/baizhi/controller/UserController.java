package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(String phone, String password, HttpSession session, String code1) {
        String code = (String) session.getAttribute("code");
        System.out.println(phone + password + code1);
        if (code1.equalsIgnoreCase(code)) {


            User user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            User u = userService.login(user);
            if (u != null) {
                session.setAttribute("name", u.getName());
                return "redirect:/menu/select";
            } else {
                return "redirect:/login.jsp";
            }

        } else {
            return "redirect:/login.jsp";
        }
    }

    @RequestMapping("inster")
    @ResponseBody
    public Map insert(User user, MultipartFile file) {
        Map map = new HashMap();
        try {
            String filename = file.getOriginalFilename();
            String path = "d:\\lun\\";
            String fileNewName = UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
            File file1 = new File(path + fileNewName);
            file.transferTo(file1);
            user.setPic(fileNewName);
            user.setDate(new Date());
            userService.insert(user);
            map.put("insert", true);
        } catch (Exception e) {
            map.put("insert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("select")
    @ResponseBody
    public Map select() {
        return userService.selectall();
    }


    @RequestMapping("up")
    public String up(int id) {
        userService.upstatus(id);
        return "redirect:/master/selectm";
    }
}

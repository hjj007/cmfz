package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

}

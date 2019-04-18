package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("select")
    public String select(HttpSession session) {
        List<Menu> list = menuService.selectall();
        session.setAttribute("list", list);
        return "main/main";
    }

    @RequestMapping("select1")
    @ResponseBody
    public Map select() {
        return menuService.selectall1();
    }
}

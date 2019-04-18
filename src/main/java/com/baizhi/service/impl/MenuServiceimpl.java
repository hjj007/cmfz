package com.baizhi.service.impl;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceimpl implements MenuService {
    @Autowired
    private MenuDao menuService;

    @Override
    public List<Menu> selectall() {
        List<Menu> list = menuService.selectall();
        return list;
    }

    @Override
    public Map selectall1() {
        Map map = new HashMap();
        List<Menu> list = menuService.selectall();
        map.put("list", list);
        return map;
    }
}

package com.baizhi.service.impl;

import com.baizhi.dao.MasterDao;
import com.baizhi.entity.Master;
import com.baizhi.service.MasterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MasterServiceimpl implements MasterService {
    @Autowired
    MasterDao masterDao;

    @Override
    public void insert(Master master) {
        masterDao.insert(master);
    }

    @Override
    public Map selectall(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<Master> pageInfo = new PageInfo<>(masterDao.selectAll());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public List<Master> select() {
        return masterDao.selectAll();
    }
}

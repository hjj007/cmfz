package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BannerServiceimpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public Map selectall(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<Banner> pageInfo = new PageInfo<>(bannerDao.selectall());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update1(banner);
    }

    @Override
    public void deleteone(int id) {
        bannerDao.deleteone(id);
    }
}

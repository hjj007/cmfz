package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.Map;

public interface BannerService {
    void insert(Banner banner);

    Map selectall(int page, int rows);

    void update(Banner banner);

    void deleteone(int id);
}

package com.baizhi.service;

import com.baizhi.entity.Master;

import java.util.List;
import java.util.Map;

public interface MasterService {
    void insert(Master master);

    Map selectall(int page, int rows);

    List<Master> select();
}

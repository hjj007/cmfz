package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;

public interface UserService {
    User login(User user);

    void insert(User user);

    Map selectall();

    void upstatus(int id);
}

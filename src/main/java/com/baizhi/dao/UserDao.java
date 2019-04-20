package com.baizhi.dao;

import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    User selectone(User user);
    void insertuser(User user);

    List<User> selectall();

    User selectone1(int id);

    void update(User user);

}

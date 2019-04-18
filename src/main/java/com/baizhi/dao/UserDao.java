package com.baizhi.dao;

import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {
    User selectone(User user);

    void insertuser(User user);
}

package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(User user) {
        User user1 = userDao.selectone(user);
        return user1;
    }

    @Override
    public void insert(User user) {
        userDao.insertuser(user);
    }

    @Override
    public Map selectall() {
        Map map = new HashMap();

        List<User> list = userDao.selectall();
        map.put("rows", list);
        map.put("total", list.size());
        return map;
    }

    @Override
    public void upstatus(int id) {
        User user = userDao.selectone1(id);
        if (user.getStatus() == 1) {
            user.setStatus(0);
        } else {
            user.setStatus(1);
        }
        userDao.update(user);

    }
}

package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceimpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public void insert(Album album) {
        albumDao.insert(album);
    }

    @Override
    public List<Album> selectall() {
        List<Album> list = albumDao.selectall();
        return list;
    }

    @Override
    public List<Album> selectAll() {
        List<Album> albums = albumDao.selectAll();
        return albums;
    }

    @Override
    public Album selectone(int id) {

        return null;
    }
}

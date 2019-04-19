package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    void insert(Album album);

    List<Album> selectall();

    List<Album> selectAll();

    Album selectone(int id);
}

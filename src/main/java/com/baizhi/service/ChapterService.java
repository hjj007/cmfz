package com.baizhi.service;

import com.baizhi.entity.Chapter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ChapterService {
    void insert(Chapter chapter);

    Chapter selectone(int id);

    void doGetFile(int id, HttpServletResponse response, String filename);

    List<Chapter> selectall();
}

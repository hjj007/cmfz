package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Album album, MultipartFile file) {
        Map map = new HashMap();
        try {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = "d:\\lun\\";
            fileName = UUID.randomUUID() + suffixName;
            File desc = new File(filePath + fileName);
            file.transferTo(desc);
            album.setPudate(new Date());
            String id = UUID.randomUUID().toString();
            album.setId(id);
            album.setAmount(0);
            album.setImgpath(fileName);
            albumService.insert(album);
            map.put("insert", true);
        } catch (Exception e) {
            map.put("insert", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("selecta")
    public String select(HttpSession session) {
        List<Album> list = albumService.selectAll();
        session.setAttribute("list", list);
        return "redirect:/album.jsp";
    }

    @RequestMapping("select")
    @ResponseBody
    public List<Album> select1() {
        return albumService.selectall();
    }

}

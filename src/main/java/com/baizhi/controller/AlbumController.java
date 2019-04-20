package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;

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


    @RequestMapping("download")
    public void download(HttpServletResponse response) {
        List<Album> list = albumService.selectall();
        for (Album album : list) {
            String imgpath = album.getImgpath();
            album.setImgpath("d:/lun/" + imgpath);
        }

        Album album = list.get(1);
        String title = album.getTitle();
        List<Chapter> list1 = chapterService.selectall();
        Chapter chapter = list1.get(1);
        String title1 = chapter.getTitle();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, title1), Album.class, list);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode("导出文件.xls", "UTF-8"))));
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

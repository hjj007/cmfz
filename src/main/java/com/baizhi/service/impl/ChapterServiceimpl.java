package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Service
public class ChapterServiceimpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void insert(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    public Chapter selectone(int id) {
        Chapter chapter = chapterDao.selectByPrimaryKey(id);
        return chapter;
    }

    @Override
    public void doGetFile(int id, HttpServletResponse response, String filename) {
        try {
            //得到要下载的文件名
            Chapter chapter = chapterDao.selectByPrimaryKey(id);
            String fileName = chapter.getFilename();
            String fileSaveRootPath = "d:\\lun\\";
            //文件路径
            File file = new File(fileSaveRootPath + fileName);
            //如果文件不存在
            if (!file.exists()) {
                System.out.println("message您要下载的资源已被删除！！");
                return;
            }
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            InputStream fis = new BufferedInputStream(new FileInputStream(fileSaveRootPath + fileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);  //读取文件流
            fis.close();//关闭文件流
            response.reset();//重置结果集
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());  //返回头 文件大小
            response.setContentType("application/octet-stream");    //设置数据种类
            ///获取返回体输出权
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            // 输出文件
            os.write(buffer);
            os.flush();
            os.close();
            System.out.println("11111111111");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Chapter> selectall() {
        List<Chapter> list = chapterDao.selectAll();
        return list;
    }


}

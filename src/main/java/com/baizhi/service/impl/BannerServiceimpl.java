package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceimpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public Map selectall(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<Banner> pageInfo = new PageInfo<>(bannerDao.selectall());
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update1(banner);
    }

    @Override
    public void deleteone(int id) {
        bannerDao.deleteone(id);
    }

    @Override
    public List<Banner> selectAll() {
        return bannerDao.selectAll();
    }

    @Override
    public Workbook deriveExcel() {
        List<Banner> list = bannerDao.selectAll();
        //创建工作簿
        Workbook workbook = new HSSFWorkbook();
        //创建字体
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("楷体");
        font.setItalic(true);
        font.setColor(Font.COLOR_RED);
        //创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);


        //创建日期格式的样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        //创建工作表
        Sheet sheet = workbook.createSheet("list");
        //第一个参数给那个列设置宽度  下标   第二个参数  宽度设置为多少  需要乘以256
        sheet.setColumnWidth(3, 20 * 256);
        //编号  名字   生日
        String[] strings = {"编号", "标题", "图片名", "创建日期", "状态"};
        //创建行  参数第几行  下标
        Row row = sheet.createRow(0);
        for (int i = 0; i < strings.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);

            cell.setCellStyle(cellStyle);
            //单元格赋值
            cell.setCellValue(strings[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getTitle());
            row1.createCell(2).setCellValue(list.get(i).getImgpath());
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(list.get(i).getCreatdate());
            row1.createCell(4).setCellValue(list.get(i).getStatus());
        }

        return workbook;
    }
}

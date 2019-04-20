package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class Album {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = " 专辑", needMerge = true)
    private String title;
    @ExcelIgnore
    private int amount;
    @Excel(name = "图片", type = 2, width = 40, height = 40, needMerge = true)
    private String imgpath;
    @Excel(name = "分数", needMerge = true)
    private String score;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音", needMerge = true)
    private String boardcast;
    @JSONField(name = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", format = "yyyy-MM-dd", width = 30, needMerge = true)
    private Date pudate;
    @Excel(name = "简介", needMerge = true)
    private String brief;
    @ExcelCollection(name = "章节")
    private List<Chapter> children;

}

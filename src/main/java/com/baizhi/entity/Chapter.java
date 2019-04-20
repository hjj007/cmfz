package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter {
    @Id
    @KeySql(useGeneratedKeys = true)
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "题目")
    private String title;
    @ExcelIgnore
    private String filename;
    @Excel(name = "大小")
    private String size;
    @Excel(name = "时长")
    private String duration;
    @JSONField(name = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传日期", format = "yyyy-MM-dd", width = 30)
    private Date publishdate;
    @ExcelIgnore
    private String albumid;
}

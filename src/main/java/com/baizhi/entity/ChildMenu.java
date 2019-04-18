package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class ChildMenu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int pid;
    private String ptitle;
    private String picon_cls;
    private int pparent_id;
    private String pjsp_url;
}

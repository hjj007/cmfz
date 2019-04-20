package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "master")
public class Master {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer mid;
    private String mdarma;
    private int mstatus = 1;
    private String mpic;
}

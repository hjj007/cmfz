package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
public class Menu {
    @Id
    @KeySql(useGeneratedKeys = true)
    private int id;
    private String title;

    private String icon_cls;
    private List<ChildMenu> childMenus;
    private int parent_id;
    private String jsp_url;

}

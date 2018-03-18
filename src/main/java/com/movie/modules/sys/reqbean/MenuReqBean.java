package com.movie.modules.sys.reqbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@ToString
public class MenuReqBean implements Serializable {

    private String title;

    private String icon;

    private String jump;

    private List<MenuReqBean> list;

    public MenuReqBean() {

    }
}

package com.c.j.w.cms.core.pojo;

/**
 * Created by kevin72c on 2016/11/2.
 */
public enum MenuTypeDict {
    Folder(0, "目录"),
    Menu(1, "子菜单"),
    Submenu(2, "子菜单");

    public final int code;
    public final String name;

    MenuTypeDict(int code, String name) {
        this.code = code;
        this.name = name;
    }
}

package com.github.cms.core.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by kevin72c on 2016/11/2.
 */
@Getter
@Setter
public class Menu {
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单类型")
    private MenuTypeDict menuType;
    @ApiModelProperty("菜单图标")
    private String iconClass;
    @ApiModelProperty("子菜单")
    private List<Menu> submenu;
    @ApiModelProperty("文件夹选项， 是否展开0否1是")
    private Integer expand;
    @ApiModelProperty("超链接地址")
    private String hrefValue;
    @ApiModelProperty("额外html内容")
    private String prepend;
}

package com.c.j.w.cms.core.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenjw
 * @date: 2017/1/11
 * @time: 15:23
 */
@Getter
@Setter
@ApiModel("系统用户")
public class User implements Serializable{

    @ApiModelProperty("用户表ID")
    private Long userID;

    @ApiModelProperty("登入账号名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty("登入密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String profilePicture;

    @ApiModelProperty(value = "角色")
    private String roleType;

    @ApiModelProperty(value = "菜单列表")
    private List<Menu> privileges;
}

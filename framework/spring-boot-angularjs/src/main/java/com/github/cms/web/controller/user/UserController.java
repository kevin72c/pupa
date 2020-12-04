package com.github.cms.web.controller.user;

import com.github.cms.core.pojo.Result;
import com.github.cms.core.pojo.User;
import com.github.cms.core.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(description = "用户")
@Controller
@RequestMapping("user")
public class UserController {

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Result login(String userName, String password) {
        User user = new User();
        user.setRoleType("Admin");
        user.setUserName(userName);
        user.setProfilePicture("resource/img/b10.jpg");
        SessionUtil.setSessionUser(user);
        return Result.success();
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        SessionUtil.clearSession();
        return "redirect:../login.html";
    }


}

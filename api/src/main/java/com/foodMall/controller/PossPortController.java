package com.foodMall.controller;

import com.foodMall.service.UserService;
import com.pojo.Users;
import com.pojo.bo.UserBO;
import com.utils.CookieUtils;
import com.utils.IMOOCJSONResult;
import com.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("possport")
public class PossPortController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username){
        if(StringUtils.isBlank(username)){
            return IMOOCJSONResult.errorMsg("用户名不能为空");
        }

        boolean result = userService.queryUsernameIsExist(username);
        if(result){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }

        return IMOOCJSONResult.ok();
    }

    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();
        //1、用户名密码不能为空
        if(StringUtils.isBlank(username) && StringUtils.isBlank(password) && StringUtils.isBlank(confirmPwd)){
            return IMOOCJSONResult.errorMsg("用户名密码不能为空");
        }
        //2、用户名是否已经存在
        boolean result = userService.queryUsernameIsExist(username);
        if(result){
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }
        //3、密码不能少过6位
        if(password.length() < 6){
            return IMOOCJSONResult.errorMsg("密码不能少于6位");
        }
        //4、两次输入密码是否一致
        if(!password.equals(confirmPwd)){
            return IMOOCJSONResult.errorMsg("两次输入密码不一致");
        }

        Users userResult = userService.createUser(userBO);
        userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return IMOOCJSONResult.ok();
    }

    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}

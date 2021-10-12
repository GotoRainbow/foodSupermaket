package com.foodMall.service;

import com.pojo.Users;
import com.pojo.bo.UserBO;

public interface UserService {
    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    boolean queryUsernameIsExist(String username);

    Users createUser(UserBO userBO);

    Users queryUserForLogin(String username,String password);
}

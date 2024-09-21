package com.campususedtrading.service;

import com.campususedtrading.pojo.User;

public interface UserService {
    // 通过用户名查找用户
    User findUserByUsername(String username);

    // 注册
    void register(String username, String password);

    // 更新用户邮箱
    void update(User user);

    // 更新用户头像
    void updateAvatar(String avatar);

    // 更新密码
    void updatePwd(String newPwd);
}

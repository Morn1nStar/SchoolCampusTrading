package com.campususedtrading.controller;


import com.campususedtrading.pojo.Result;
import com.campususedtrading.pojo.User;
import com.campususedtrading.service.UserService;
import com.campususedtrading.util.JwtUtil;
import com.campususedtrading.util.Md5Util;
import com.campususedtrading.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController // 将返回值作为 HTTP 响应体返回给客户端，而不是解析为一个视图名或重定向 URL
@RequestMapping("/user") // 用于将HTTP请求映射到特定的处理器类或处理器方法上
@Validated
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;// 用于实现依赖注入

    // 注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{1,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return Result.error("用户名以被占用");
        }
        else {
            userService.register(username, password);
            return Result.success();
        }
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{1,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result.error("用户名错误");
        }

        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",username);
            String token = JwtUtil.genToken(map);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    // 获取用户详细信息
    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        User user = userService.findUserByUsername(username);
        return Result.success(user);
    }

    // 更新用户邮箱
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    // 更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatar) {
        userService.updateAvatar(avatar);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token) {
        // 校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePdw = params.get("re_pwd");

        // 原密码是否正确
        // 调用uerService根据用户名拿到原密码与old_pwd进行比较
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        User user = userService.findUserByUsername(username);
        if(!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        // newPwd是否与rePwd相同
        if (!rePdw.equals(newPwd)){
            return Result.error("两次填写的新密码不同");
        }

        // 调用service
        userService.updatePwd(newPwd);
        return Result.success();
    }
}

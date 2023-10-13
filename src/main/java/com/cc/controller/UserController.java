package com.cc.controller;

import com.alibaba.druid.util.StringUtils;
import com.cc.pojo.User;
import com.cc.service.UserService;
import com.cc.utils.JwtHelper;
import com.cc.utils.Result;
import com.cc.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringBoot-headline
 * @ClassName UserController
 * @author: c9noo
 * @create: 2023-10-13 16:29
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin  //TODO 允许跨域请求
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 用户登录业务
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    @PostMapping("/checkUserName")
    public Result checkUserName(String username) {
        return userService.checkUserName(username);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/regist")
    public Result regist(@RequestBody User user){
        return userService.regist(user);
    }

    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)){
            //没有传或者过期 未登录
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }

}

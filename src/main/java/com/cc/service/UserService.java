package com.cc.service;

import com.cc.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.utils.Result;

/**
* @author 49751
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-13 15:21:31
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录业务
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 用户注册模块
     * @param user
     * @return
     */
    Result regist(User user);
}

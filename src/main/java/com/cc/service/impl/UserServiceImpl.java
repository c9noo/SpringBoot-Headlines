package com.cc.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.pojo.User;
import com.cc.service.UserService;
import com.cc.mapper.UserMapper;
import com.cc.utils.JwtHelper;
import com.cc.utils.MD5Util;
import com.cc.utils.Result;
import com.cc.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author 49751
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-13 15:21:31
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 用户登录
     *  前端传送来的是用户名 和密码
     *  根据用户名查找数据库的用户-->如果不存在就直接结束
     *  判断密码是否匹配
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(loginUser)){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (StringUtils.isEmpty(user.getUserPwd()) &&
                !(MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd()))){
            return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }
        Map map = new HashMap<>();
        map.put("token",jwtHelper.createToken(Long.valueOf(loginUser.getUid())));
        return Result.ok(map);
    }


    /**
     * 根据token获取用户信息
     * 判断token是否过期
     * 判断id是否存在
     * 返回结构
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        if (jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        User user = userMapper.selectById(jwtHelper.getUserId(token));
        if (Objects.isNull(user)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        user.setUserPwd("");
        Map map = new HashMap<>();
        map.put("loginUser",user);
        return Result.ok(map);
    }

    /**
     * 判断用户名是否存在
     * 将用户名放入数据库进行比对
     * 如果返回null 代表可用
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (!Objects.isNull(user)){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        return Result.ok(null);
    }

    /**
     * 用户注册
     * 判断账号是否重复-->重复直接返回信息
     * 密码加密，存入数据库
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Result regist(User user) {
        if (Integer.valueOf("505").equals(checkUserName(user.getUsername()).getCode())){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int row = userMapper.insert(user);
        if (row != 1){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }
}





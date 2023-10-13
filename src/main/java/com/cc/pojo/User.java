package com.cc.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_user
 */

@Data
public class User implements Serializable {
    @TableId  //TODO 如果不写的话，那么调用mybatis plus提供的方法 可能找不到id
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;
    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}
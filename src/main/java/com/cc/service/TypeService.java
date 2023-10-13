package com.cc.service;

import com.cc.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.utils.Result;

/**
* @author 49751
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-10-13 15:21:31
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询分类信息
     * @return
     */
    Result findAllTypes();

}

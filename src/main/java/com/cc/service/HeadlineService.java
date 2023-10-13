package com.cc.service;

import com.cc.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.pojo.PortalVo;
import com.cc.utils.Result;

/**
* @author 49751
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-13 15:21:31
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 分页查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据id查询详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条
     * @param headline
     * @return
     */
    Result publish(Headline headline);

    /**
     *回显头条
     * @param hid
     * @return
     */
    Result findHeadlineByHid(Integer hid);

    /**
     * 修改头条
     * @param headline
     * @return
     */
    Result updateHeadLine(Headline headline);
}

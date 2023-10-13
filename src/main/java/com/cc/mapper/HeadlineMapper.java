package com.cc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 49751
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-13 15:21:31
* @Entity com.cc.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectPageMap(IPage<Headline> page,
                             @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);
}





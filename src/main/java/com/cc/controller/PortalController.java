package com.cc.controller;

import com.cc.pojo.PortalVo;
import com.cc.service.HeadlineService;
import com.cc.service.TypeService;
import com.cc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringBoot-headline
 * @ClassName TypeController
 * @author: c9noo
 * @create: 2023-10-13 19:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询分类信息
     * @return
     */
    @GetMapping("/findAllTypes")
    public Result findAllTypes(){
        return typeService.findAllTypes();
    }

    /**
     * 分页查询
     * @param portalVo
     * @return
     */
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        return headlineService.findNewsPage(portalVo);
    }

    @PostMapping("/showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        return headlineService.showHeadlineDetail(hid);
    }
}

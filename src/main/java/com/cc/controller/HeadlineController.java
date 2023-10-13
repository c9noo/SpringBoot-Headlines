package com.cc.controller;

import com.cc.pojo.Headline;
import com.cc.service.HeadlineService;
import com.cc.utils.JwtHelper;
import com.cc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringBoot-headline
 * @ClassName HeadlineController
 * @author: c9noo
 * @create: 2023-10-13 20:05
 * @Version 1.0
 **/
@RestController
@RequestMapping("/headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private HeadlineService headlineService;


    /**
     * 发布头条
     * @param headline
     * @param token
     * @return
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        return headlineService.publish(headline);
    }

    /**
     * 回显头条
     * @param hid
     * @return
     */
    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        return headlineService.findHeadlineByHid(hid);
    }

    /**
     * 修改头条
     * @param headline
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Headline headline) {
        return headlineService.updateHeadLine(headline);
    }

    /**
     * 删除头条
     * @param hid
     * @return
     */
    @PostMapping("/removeByHid")
    public Result removeById(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}

package net.arver.onlineedu.controller;

import net.arver.onlineedu.config.WeChatConfig;
import net.arver.onlineedu.domain.JsonData;
import net.arver.onlineedu.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private VideoMapper videoMapper;

    @RequestMapping("test_config")
    public JsonData testConfig() {
        System.out.println(weChatConfig.getAppId());
        System.out.println(weChatConfig.getAppSecret());
        return JsonData.buildSuccess(weChatConfig.getAppId());
    }

    @RequestMapping("test_db")
    public Object testDB() {
        return videoMapper.findAll();
    }
}

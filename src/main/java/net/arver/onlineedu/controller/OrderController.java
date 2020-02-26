package net.arver.onlineedu.controller;

import net.arver.onlineedu.domain.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制器.
 */
@RestController
@RequestMapping("/user/api/v1")
public class OrderController {

    /**
     * 保存订单.
     * @return 操作结果
     */
    @GetMapping("add")
    public JsonData saveOrder() {
        return JsonData.buildSuccess();
    }
}

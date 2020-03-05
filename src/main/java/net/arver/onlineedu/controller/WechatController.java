package net.arver.onlineedu.controller;


import net.arver.onlineedu.config.WeChatConfig;
import net.arver.onlineedu.domain.JsonData;
import net.arver.onlineedu.domain.User;
import net.arver.onlineedu.domain.VideoOrder;
import net.arver.onlineedu.service.UserService;
import net.arver.onlineedu.service.VideoOrderService;
import net.arver.onlineedu.util.JwtUtils;
import net.arver.onlineedu.util.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

@Controller
@RequestMapping("/api/v1/wechat")
public class WechatController {

    /**
     * 微信配置.
     */
    @Autowired
    private WeChatConfig weChatConfig;

    /**
     * 用户服务.
     */
    @Autowired
    private UserService userService;

    /**
     * 订单服务.
     */
    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 拼装微信扫一扫登录url.
     * @param accessPage 回调页面
     * @return 登录url
     */
    @GetMapping("login_url")
    @ResponseBody
    public JsonData loginUrl(@RequestParam(value = "access_page")final String accessPage) throws UnsupportedEncodingException {

        final String redirectUrl = weChatConfig.getOpenRedirectUrl();

        final String callbackUrl = URLEncoder.encode(redirectUrl, "GBK");

        final String qrcodeUrl = String.format(WeChatConfig.getOpenQrcodeUrl(), weChatConfig.getOpenAppid(), callbackUrl, accessPage);

        return JsonData.buildSuccess(qrcodeUrl);
    }

    /**
     * 微信登录毁掉.
     * @param code code
     * @param state state
     * @param response response
     */
    @GetMapping("/user/callback")
    public void wechatUserCallback(@RequestParam(value = "code")final String code,
                                   final String state, final HttpServletResponse response) throws IOException {
        final User user = userService.saveWeChatUser(code);
        if (user != null) {
            // 生成jwt
            final String token = JwtUtils.genToken(user);
            response.sendRedirect(state + "?token=" + token + "&head_img=" + user.getHeadImg() + "&name=" + URLEncoder.encode(user.getName(), "UTF-8"));
        }
    }

    /**
     * 微信支付回调.
     * @param request 请求
     * @param response 响应
     */
    @PostMapping("/order/callback")
    public void orderCallback(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final ServletInputStream inputStream = request.getInputStream();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        final StringBuffer sb = new StringBuffer();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        bufferedReader.close();
        inputStream.close();
        final Map<String, String> callbackMap = WXPayUtil.xmlToMap(sb.toString());

        System.out.println(callbackMap.toString());

        final SortedMap<String, String> sortedMap = WXPayUtil.getSortedMap(callbackMap);
        if (WXPayUtil.isCorrectSign(sortedMap, weChatConfig.getKey())) {
            if ("SUCCESS".equals(sortedMap.get("result_code"))) {
                final String outTradeNo = sortedMap.get("out_trade_no");
                final VideoOrder dbVideoOrder = videoOrderService.findByOutTradeNo(outTradeNo);
                if (dbVideoOrder != null && dbVideoOrder.getState() == 0) {
                    final VideoOrder videoOrder = new VideoOrder();
                    videoOrder.setOpenid(sortedMap.get("openid"));
                    videoOrder.setOutTradeNo(outTradeNo);
                    videoOrder.setNotifyTime(new Date());
                    videoOrder.setState(1);
                    final int rows = videoOrderService.updateVideoOrderByOutTradeNo(videoOrder);
                    if (rows == 1) { // 通知微信订单处理成功
                        response.setContentType("text/xml");
                        response.getWriter().println("success");
                        return;
                    }
                }
            }
        }
        //处理失败
        response.setContentType("text/xml");
        response.getWriter().println("fail");
    }


}

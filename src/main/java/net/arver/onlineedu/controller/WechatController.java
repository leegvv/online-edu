package net.arver.onlineedu.controller;


import net.arver.onlineedu.config.WeChatConfig;
import net.arver.onlineedu.domain.JsonData;
import net.arver.onlineedu.domain.User;
import net.arver.onlineedu.service.UserService;
import net.arver.onlineedu.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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



}

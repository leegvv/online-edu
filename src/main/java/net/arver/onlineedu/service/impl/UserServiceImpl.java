package net.arver.onlineedu.service.impl;

import net.arver.onlineedu.config.WeChatConfig;
import net.arver.onlineedu.domain.User;
import net.arver.onlineedu.mapper.UserMapper;
import net.arver.onlineedu.service.UserService;
import net.arver.onlineedu.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    /**
     * 微信配置类型.
     */
    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(final String code) {
        final String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(), weChatConfig.getOpenAppid(),
            weChatConfig.getOpenAppsecret(), code);
        final Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if (CollectionUtils.isEmpty(baseMap)) {
            return null;
        }

        final String accessToken = (String) baseMap.get("access_token");
        final String openId = (String) baseMap.get("openid");

        final User dbUser = userMapper.findByopenid(openId);
        if (dbUser != null) {
            return dbUser;
        }

        //获取用户基本信息
        final String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(), accessToken, openId);
        final Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if (CollectionUtils.isEmpty(baseUserMap)) {
            return null;
        }
        String nickname = (String) baseUserMap.get("nickname");
        Double sexTemp  = (Double) baseUserMap.get("sex");
        final int sex = sexTemp.intValue();
        final String province = (String) baseUserMap.get("province");
        final String city = (String) baseUserMap.get("city");
        final String country = (String) baseUserMap.get("country");
        final String headimgurl = (String) baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        return user;
    }
}

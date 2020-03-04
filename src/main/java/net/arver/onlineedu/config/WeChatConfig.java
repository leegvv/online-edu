package net.arver.onlineedu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeChatConfig {

    /**
     * 公众号appid.
     */
    @Value("${wxpay.appid}")
    private String appId;

    /**
     * 公众号秘钥.
     */
    @Value("${wxpay.appsecret}")
    private String appSecret;

    /**
     * 开放平台appid.
     */
    @Value("${wxopen.appid}")
    private String openAppid;

    /**
     * 开放平台秘钥.
     */
    @Value("${wxopen.appsecret}")
    private String openAppsecret;

    @Value("${wxopen.redirect_url}")
    private String openRedirectUrl;

    /**
     * 微信开放平台二维码连接
     */
    private final static String OPEN_QRCODE_URL= "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=%s#wechat_redirect";

    /**
     * 开放平台获取access_token地址
     */
    private final static String OPEN_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 获取用户信息
     */
    private final static String OPEN_USER_INFO_URL ="https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 商户号id.
     */
    @Value("${wxpay.mer_id}")
    private String mchId;

    /**
     * 支付key.
     */
    @Value("${wxpay.key}")
    private String key;

    /**
     * 微信支付回调url.
     */
    @Value("${wxpay.callback}")
    private String payCallbackUrl;

    /**
     * 统一下单url
     */
    //private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String UNIFIED_ORDER_URL = "http://api.xdclass.net:8081/pay/unifiedorder";

    public String getAppId() {
        return appId;
    }

    public void setAppId(final String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(final String appSecret) {
        this.appSecret = appSecret;
    }

    public String getOpenAppid() {
        return openAppid;
    }

    public void setOpenAppid(final String openAppid) {
        this.openAppid = openAppid;
    }

    public String getOpenAppsecret() {
        return openAppsecret;
    }

    public void setOpenAppsecret(final String openAppsecret) {
        this.openAppsecret = openAppsecret;
    }

    public String getOpenRedirectUrl() {
        return openRedirectUrl;
    }

    public void setOpenRedirectUrl(final String openRedirectUrl) {
        this.openRedirectUrl = openRedirectUrl;
    }

    public static String getOpenQrcodeUrl() {
        return OPEN_QRCODE_URL;
    }

    public static String getOpenAccessTokenUrl() {
        return OPEN_ACCESS_TOKEN_URL;
    }

    public static String getOpenUserInfoUrl() {
        return OPEN_USER_INFO_URL;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(final String mchId) {
        this.mchId = mchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getPayCallbackUrl() {
        return payCallbackUrl;
    }

    public void setPayCallbackUrl(final String payCallbackUrl) {
        this.payCallbackUrl = payCallbackUrl;
    }

    public static String getUnifiedOrderUrl() {
        return UNIFIED_ORDER_URL;
    }
}

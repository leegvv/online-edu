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
}

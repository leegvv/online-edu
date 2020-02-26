package net.arver.onlineedu.service;

import net.arver.onlineedu.domain.User;

public interface UserService {

    /**
     * 获取维护用户信息.
     * @param code code
     * @return 用户信息
     */
    User saveWeChatUser(String code);
}

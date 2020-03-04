package net.arver.onlineedu.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * 常用工具类封装.
 */
public class CommonUtils {

    /**
     * 生成uuid.
     * @return uuid
     */
    public static String generateUUID() {
        final String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
        return uuid;
    }

    /**
     * md5常用工具类.
     * @param data md5加密参数
     * @return md5加密值
     */
    public static String MD5(final String data) {
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            final byte[] array = md5.digest(data.getBytes("UTF-8"));
            final StringBuilder sb = new StringBuilder();
            for (final byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

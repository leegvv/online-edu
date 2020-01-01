package net.arver.onlineedu.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.arver.onlineedu.domain.User;

import java.util.Date;

public class JwtUtil {

    public static final String SUBJECT = "onlineedu";

    /**
     * 过期时间.
     */
    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 秘钥.
     */
    public static final String APPSECRET = "arver666";

    /**
     * 生成jwt.
     * @param user 用户
     * @return token
     */
    public static String genToken(final User user) {

        if (user == null || user.getId() == null || user.getName() == null || user.getHeadImg() == null) {
            return null;
        }
        final String token = Jwts.builder().setSubject(SUBJECT)
            .claim("id", user.getId())
            .claim("name", user.getName())
            .claim("img", user.getHeadImg())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
            .signWith(SignatureAlgorithm.HS256, APPSECRET)
            .compact();
        return token;
    }

    /**
     * 校验token.
     * @param token token
     * @return 用户数据
     */
    public static Claims validateToken(final String token) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (final Exception e) {
            return null;
        }
    }
}

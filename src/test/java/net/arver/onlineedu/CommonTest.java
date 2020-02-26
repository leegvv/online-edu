package net.arver.onlineedu;

import io.jsonwebtoken.Claims;
import net.arver.onlineedu.domain.User;
import net.arver.onlineedu.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommonTest {

    @Test
    public void testGenToken() {
        final User user = new User();
        user.setId(999);
        user.setHeadImg("aaaa.jpg");
        user.setName("arver");
        final String token = JwtUtils.genToken(user);
        System.out.println(token);
    }

    @Test
    public void testValidateToken() {
        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvbmxpbmVlZHUiLCJpZCI6OTk5LCJuYW1lIjoiYXJ2ZXIiLCJpbWciOiJhYWFhLmpwZyIsImlhdCI6MTU3NzgxMjg2NCwiZXhwIjoxNTc4NDE3NjY0fQ.4EbqeVLmvxUOOingqpDuVJd1NKj5dRheLW0aUR0BvW4";
        final Claims claims = JwtUtils.checkJwt(token);
        if (claims != null) {
            final Integer id = (Integer) claims.get("id");
            final String name = (String) claims.get("name");
            final String img = (String) claims.get("img");
            System.out.println(id);
            System.out.println(name);
            System.out.println(img);
        } else {
            System.out.println("非法token");
        }
    }

}

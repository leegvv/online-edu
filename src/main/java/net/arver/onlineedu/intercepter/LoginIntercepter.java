package net.arver.onlineedu.intercepter;

import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import net.arver.onlineedu.domain.JsonData;
import net.arver.onlineedu.util.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {

    /**
     * json工具类.
     */
    private static final Gson GSON = new Gson();

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        if (!StringUtils.isEmpty(token)) {
            final Claims claims = JwtUtils.checkJwt(token);
            if (claims != null) {
                final Integer userId = (Integer) claims.get("id");
                final String name = (String) claims.get("name");
                request.setAttribute("user_id", userId);
                request.setAttribute("name", name);
                return true;
            }
        }

        sendJsonMessage(response, JsonData.buildError("请登录"));

        return false;
    }

    /**
     * 响应数据给前端.
     * @param response 响应
     * @param obj 数据
     */
    public static void sendJsonMessage(final HttpServletResponse response, final Object obj) {
        try {
            response.setContentType("application/json; charset=utf-8");
            final PrintWriter writer = response.getWriter();
            writer.print(GSON.toJson(obj));
            writer.close();
            response.flushBuffer();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


}

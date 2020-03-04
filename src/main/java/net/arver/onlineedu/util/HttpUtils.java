package net.arver.onlineedu.util;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装http方法.
 */
public class HttpUtils {

    /**
     * json工具.
     */
    private static final Gson gson = new Gson();

    /**
     * get方法封装.
     * @param url url
     * @return 结果
     */
    public static Map<String, Object> doGet(final String url) {

        HashMap<String, Object> map = new HashMap<>();
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .setSocketTimeout(5000)
            .setRedirectsEnabled(true)
            .build();
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try {
            final CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                final String jsonResult = EntityUtils.toString(httpResponse.getEntity());
                map = gson.fromJson(jsonResult, map.getClass());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * post方法.
     * @param url url
     * @param data 数据
     * @param timeout 超时时间
     * @return 结果
     */
    public static String doPost(final String url, final String data, final int timeout) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout)
            .setConnectionRequestTimeout(timeout)
            .setRedirectsEnabled(true)
            .build();
        final HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        //httpPost.addHeader("Content-Type", "text/html; charset=UTF-8");
        httpPost.addHeader("Content-Type","text/html; charset=UTF-8");
        if (data != null && data instanceof String) {
            final StringEntity stringEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(stringEntity);
        }

        try {
            final CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            final HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                final String result = EntityUtils.toString(httpEntity, "UTF-8");
                return result;
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

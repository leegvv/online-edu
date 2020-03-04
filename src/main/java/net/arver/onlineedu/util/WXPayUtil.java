package net.arver.onlineedu.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付工具类.
 */
public class WXPayUtil {

    /**
     * 将xml格式字符创转换为map.
     * @param xmlStr xml格式字符串
     * @return 转换后的map
     * @throws Exception 异常
     */
    public static Map<String, String> xmlToMap(final String xmlStr) throws Exception {
        final Map<String, String> data = new HashMap<>();
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        final InputStream stream = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
        final Document doc = documentBuilder.parse(stream);
        doc.getDocumentElement().normalize();
        final NodeList nodeList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            final Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                data.put(element.getNodeName(), element.getTextContent());
            }
        }
        try {
            stream.close();
        } catch (Exception e) {
            // do nothing
        }
        return data;
    }

    /**
     * 将map转换为xml格式字符串.
     * @param data map数据
     * @return xml个税字符串
     * @throws Exception
     */
    public static String mapToXml(final Map<String, String> data) throws Exception {
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        final Document document = documentBuilder.newDocument();
        final Element root = document.createElement("xml");
        document.appendChild(root);
        for (final String key : data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            final Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        final TransformerFactory ts = TransformerFactory.newInstance();
        final Transformer transformer = ts.newTransformer();
        final DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        final StringWriter stringWriter = new StringWriter();
        final StreamResult streamResult = new StreamResult(stringWriter);
        transformer.transform(source, streamResult);
        final String output = stringWriter.getBuffer().toString();
        try {
            stringWriter.close();
        } catch (IOException e) {
            // do nothing
        }
        return output;
    }

    /**
     * 生成微信支付sign.
     * @param params 参数
     * @param key key
     * @return sign
     */
    public static String createSign(final SortedMap params, final String key) {
        final StringBuilder sb = new StringBuilder();
        final Set<Map.Entry<String, String>> es = params.entrySet();
        final Iterator it = es.iterator();

        //生成 stringA="appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
        while (it.hasNext()) {
            final Map.Entry<String, String> entity = (Map.Entry<String, String>) it.next();
            final String k = entity.getKey();
            final String v = entity.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)){
                sb.append(k+"="+v+"&");
            }
        }
        sb.append("key=").append(key);
        final String sign = CommonUtils.MD5(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 校验签名.
     * @param params 参数
     * @param key key
     * @return 签名是否正确
     */
    public static boolean isCorrectSign(final SortedMap<String, String> params, final String key) {
        final String sign = createSign(params, key);
        final String weixinPaySign = params.get("sign").toUpperCase();
        return weixinPaySign.equals(sign);
    }

    /**
     * 获取有序map.
     * @param map map
     * @return 有序map
     */
    public static SortedMap<String, String> getSortedMap(final Map<String, String> map) {
        final SortedMap sortedMap = new TreeMap();
        final Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            final String key = it.next();
            final String value = map.get(key);
            String temp = "";
            if (value != null) {
                temp = value.trim();
            }
            sortedMap.put(key, temp);
        }
        return sortedMap;
    }



}

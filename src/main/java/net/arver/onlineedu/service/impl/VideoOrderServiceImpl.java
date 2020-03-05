package net.arver.onlineedu.service.impl;

import net.arver.onlineedu.config.WeChatConfig;
import net.arver.onlineedu.domain.User;
import net.arver.onlineedu.domain.Video;
import net.arver.onlineedu.domain.VideoOrder;
import net.arver.onlineedu.dto.VideoOrderDto;
import net.arver.onlineedu.exception.ServiceException;
import net.arver.onlineedu.mapper.UserMapper;
import net.arver.onlineedu.mapper.VideoMapper;
import net.arver.onlineedu.mapper.VideoOrderMapper;
import net.arver.onlineedu.service.VideoOrderService;
import net.arver.onlineedu.util.CommonUtils;
import net.arver.onlineedu.util.HttpUtils;
import net.arver.onlineedu.util.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String save(final VideoOrderDto videoOrderDto){

        dataLogger.info("module=video_order`api=save`user_id={}", videoOrderDto.getUserId());

        logger.info("开发处理订单");
        logger.error("订单异常");

        // 查找视频信息
        final Video video = videoMapper.findById(videoOrderDto.getVideoId());

        // 查找用户信息
        final User user = userMapper.findByid(videoOrderDto.getUserId());

        // 生成订单
        final VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setVideoId(video.getId());

        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        videoOrder.setDel(0);
        videoOrder.setIp(videoOrderDto.getIp());
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());

        videoOrderMapper.insert(videoOrder);

        // 获取codeurl
        final String codeUrl;
        try {
            codeUrl = unifiedOrder(videoOrder);
        } catch (Exception e) {
            throw new ServiceException(-1, "统一下单异常");
        }

        return codeUrl;
    }

    @Override
    public VideoOrder findByOutTradeNo(final String outTradeNo) {
        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public int updateVideoOrderByOutTradeNo(final VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOrderByOutTradeNo(videoOrder);
    }

    /**
     * 统一下单方法.
     * @param videoOrder 订单
     * @return
     * @throws Exception
     */
    private String unifiedOrder(final VideoOrder videoOrder) throws Exception {

        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",weChatConfig.getAppId());
        params.put("mch_id", weChatConfig.getMchId());
        params.put("nonce_str", CommonUtils.generateUUID());
        params.put("body",videoOrder.getVideoTitle());
        params.put("out_trade_no",videoOrder.getOutTradeNo());
        params.put("total_fee",videoOrder.getTotalFee().toString());
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallbackUrl());
        params.put("trade_type","NATIVE");

        // 生成签名
        final String sign = WXPayUtil.createSign(params, weChatConfig.getKey());
        params.put("sign", sign);

        // map转xml
        final String payXml = WXPayUtil.mapToXml(params);

        // 统一下单
        final String orderStr = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(), payXml, 4000);
        if (orderStr == null) {
            return null;
        }

        final Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);

        if (unifiedOrderMap != null) {
            return unifiedOrderMap.get("code_url");
        }

        return null;
    }

}

package net.arver.onlineedu.service;

import net.arver.onlineedu.domain.VideoOrder;
import net.arver.onlineedu.dto.VideoOrderDto;

/**
 * 订单接口.
 */
public interface VideoOrderService {

    /**
     * 保存订单.
     * @param videoOrderDto 订单dto
     * @return 订单
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据流水号查找订单.
     * @param outTradeNo 流水号
     * @return 订单
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 根据流水号更新订单.
     * @param videoOrder 订单
     * @return 更新记录数
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);

}

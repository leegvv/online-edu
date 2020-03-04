package net.arver.onlineedu.service;

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
}

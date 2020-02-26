package net.arver.onlineedu.mapper;

import net.arver.onlineedu.domain.VideoOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 订单dao.
 */
@Mapper
public interface VideoOrderMapper {

    /**
     * 保存订单，返回主键.
     * @param videoOrder 订单
     * @return id
     */
    @Insert("INSERT INTO `video_order` (`openid`, `out_trade_no`, `state`, `create_time`," +
        " `notify_time`, `total_fee`, `nickname`, `head_img`, `video_id`, `video_title`," +
        " `video_img`, `user_id`, `ip`, `del`)" +
        "VALUES" +
        "(#{openid},#{outTradeNo},#{state},#{createTime},#{notifyTime},#{totalFee}," +
        "#{nickname},#{headImg},#{videoId},#{videoTitle},#{videoImg},#{userId},#{ip},#{del})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(VideoOrder videoOrder);

    /**
     * 根据主键查找订单.
     * @param id 主键
     * @return 订单
     */
    @Select("select * from video_order where id = #{order_id} and del = 0")
    VideoOrder findById(@Param("order_id") int id);

    /**
     * 根据交易订单号获取订单对象.
     * @param outTradeNo 交易订单号
     * @return 订单
     */
    @Select("select * from video_order where out_trade_no = #{out_trade_no} and del = 0")
    VideoOrder findByOutTradeNo(@Param("out_trade_no") String outTradeNo);

    /**
     * 逻辑删除订单.
     * @param id 主键
     * @param userId 与用户id
     * @return 删除的记录数
     */
    @Update("update video_order set del = 0 where id = #{id} and user_id = #{userId}")
    int delete(@Param("id") int id, @Param("userId") int userId);


}

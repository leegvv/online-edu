package net.arver.onlineedu.provider;

import net.arver.onlineedu.domain.Video;
import org.apache.ibatis.jdbc.SQL;

public class VideoSqlProvider {

    public String updateByPrimaryKey(final Video video) {
        return new SQL() {{
            UPDATE("video");
            SET("title = #{title}");
            SET("summary = #{summary}");
            SET("cover_img = #{coverImg}");
            SET("view_num = #{viewNum}");
            SET("price = #{price}");
            SET("create_time = #{createTime}");
            SET("online = #{online}");
            SET("point = #{point}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String updateByPrimaryKeySelective(final Video video) {
        return new SQL() {{
            UPDATE("video");
            if (video.getTitle() != null) {
                SET("title = #{title}");
            }
            if (video.getSummary() != null) {
                SET("summary = #{summary}");
            }
            if (video.getCoverImg() != null) {
                SET("cover_img = #{coverImg}");
            }
            if (video.getViewNum() != null) {
                SET("view_num = #{viewNum}");
            }
            if (video.getPrice() != null) {
                SET("price = #{price}");
            }
            if (video.getCreateTime() != null) {
                SET("create_time = #{createTime}");
            }
            if (video.getOnline() != null) {
                SET("online = #{online}");
            }
            if (video.getPoint() != null) {
                SET("point = #{point}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}

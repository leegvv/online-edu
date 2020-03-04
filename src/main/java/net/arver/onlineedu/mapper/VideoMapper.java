package net.arver.onlineedu.mapper;

import net.arver.onlineedu.domain.Video;
import net.arver.onlineedu.provider.VideoSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * video数据访问层.
 */
@Mapper
public interface VideoMapper {

    @Select("SELECT * FROM video")
    List<Video> findAll();

    @Select("SELECT * FROM video WHERE id = #{id}")
    Video findById(int id);

    @UpdateProvider(type = VideoSqlProvider.class, method = "updateByPrimaryKey")
    int updateByPrimaryKey(Video video);

    @UpdateProvider(type = VideoSqlProvider.class, method = "updateByPrimaryKeySelective")
    int update(Video video);

    @Delete("DELETE FROM video WHERE id = #{id}")
    int delete(int id);

    @Insert("INSERT INTO `video` ( `title`, `summary`, " +
        "`cover_img`, `view_num`, `price`, `create_time`," +
        " `online`, `point`)" +
        "VALUES" +
        "(#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price},#{createTime}" +
        ",#{online},#{point});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(Video video);
}

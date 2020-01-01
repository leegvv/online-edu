package net.arver.onlineedu.service;


import net.arver.onlineedu.domain.Video;

import java.util.List;

/**
 * 视频服务接口.
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video video);

    int delete(int id);

    int save(Video video);
}

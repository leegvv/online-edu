package net.arver.onlineedu.service.impl;

import net.arver.onlineedu.domain.Video;
import net.arver.onlineedu.mapper.VideoMapper;
import net.arver.onlineedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(final int id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(final Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(final int id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(final Video video) {
        return videoMapper.save(video);
    }
}


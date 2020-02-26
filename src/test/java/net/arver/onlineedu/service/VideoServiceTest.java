package net.arver.onlineedu.service;

import net.arver.onlineedu.domain.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    void findAll() {
        final List<Video> list = videoService.findAll();
        assertNotNull(list);
        for (final Video video : list) {
            System.out.println(video.getTitle());
        }
    }

    @Test
    void findById() {
        final Video video = videoService.findById(1);
        assertNotNull(video);
        System.out.println(video.getTitle());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void save() {
    }
}

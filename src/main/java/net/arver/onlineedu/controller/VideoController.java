package net.arver.onlineedu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.arver.onlineedu.domain.Video;
import net.arver.onlineedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("page")
    public Object pageVideo(@RequestParam(value = "page", defaultValue = "1") final int page,
                            @RequestParam(value = "size", defaultValue = "5") final int size) {
        PageHelper.startPage(page, size);
        final List<Video> list = videoService.findAll();
        final PageInfo<Video> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @GetMapping("find_by_id")
    public Object findById(@RequestParam(value = "video_id") final int videoId) {
        return videoService.findById(videoId);
    }

}

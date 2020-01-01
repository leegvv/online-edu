package net.arver.onlineedu.controller.admin;

import net.arver.onlineedu.domain.Video;
import net.arver.onlineedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("save")
    public Object save(@RequestBody final Video video) {
        return videoService.save(video);
    }

    @PutMapping("update_by_id")
    public Object update(@RequestBody final Video video) {
        return videoService.update(video);
    }

    @DeleteMapping("del_by_id")
    public Object delById(@RequestParam("video_id") final int videoId) {
        return videoService.delete(videoId);
    }
}

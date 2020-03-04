package net.arver.onlineedu.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.arver.onlineedu.dto.VideoOrderDto;
import net.arver.onlineedu.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单控制器.
 */
@RestController
//@RequestMapping("/user/api/v1/order")
@RequestMapping("/api/v1/order")
public class OrderController {

    /**
     * 订单服务服务.
     */
    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 保存订单.
     * @param videoId 视频id
     * @param response response
     * @return 操作结果
     */
    @GetMapping("add")
    public void saveOrder(@RequestParam("video_id") final int videoId,
                              final HttpServletResponse response) throws Exception {
        //final String ip = IpUtils.getIpAddr(request);
        //final Integer userId = (Integer) request.getAttribute("user_id");
        int userId = 1;
        final String ip = "192.168.3.6";
        final VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);
        final String codeUrl = videoOrderService.save(videoOrderDto);
        if (codeUrl == null) {
            throw new NullPointerException();
        }

        try {
            // 生成二维码
            final Map<EncodeHintType, Object> hints = new HashMap<>();
            // 设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            final BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400, hints);
            final ServletOutputStream out = response.getOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}

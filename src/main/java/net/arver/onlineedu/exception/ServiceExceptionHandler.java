package net.arver.onlineedu.exception;

import net.arver.onlineedu.domain.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理器.
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(final Exception e) {
        if (e instanceof ServiceException) {
            final ServiceException serviceException = (ServiceException) e;
            return JsonData.buildError(serviceException.getMsg(), serviceException.getCode());
        } else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }

}

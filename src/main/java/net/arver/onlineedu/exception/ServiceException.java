package net.arver.onlineedu.exception;

/**
 * 自定义异常类.
 */
public class ServiceException extends RuntimeException{

    /**
     * code.
     */
    private int code;

    /**
     * 异常信息.
     */
    private String msg;

    /**
     * 无参构造函数.
     */
    public ServiceException() {
        super();
    }

    /**
     * 全参构造函数.
     * @param code code
     * @param msg 异常信息
     */
    public ServiceException(final int code, final String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }
}

package net.arver.onlineedu.domain;

import java.io.Serializable;

public class JsonData implements Serializable {

    private Integer code;

    private Object data;

    private String msg;

    public JsonData(){}

    public JsonData(final Integer code, final Object data, final String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    public static JsonData buildSuccess(final Object data) {
        return new JsonData(0, data, null);
    }

    public static JsonData buildError(final String msg) {
        return new JsonData(-1, null, msg);
    }

    public static JsonData buildError(final String msg, final Integer code) {
        return new JsonData(code, null, msg);
    }

    public static JsonData buildSuccess(final Object data, final String msg) {
        return new JsonData(-1, data, msg);
    }

    public static JsonData buildSuccess(final Object data, final Integer code) {
        return new JsonData(code, data, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(final Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }
}

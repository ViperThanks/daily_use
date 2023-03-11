package com.yiren.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * author  : wl
 * email   : vieper0714@outlook.com
 * date     : 2023/2/24 16:30
 * desc     :
 */
@Setter
@Getter
@ToString
public class Result<T> {

    /**
     * code == 1 成功 , code == 0 失败
     */
    private Integer code;

    /**
     * data 泛型数据, 给前端返回的数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String message;


    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * return Result instance code = 1 and data = parameter data
     * @param data 需要返回的数据
     * @return Result 对象
     * @param <T> data的泛型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(1, data);
    }

    /**
     * return Result instance code = 0 and message = parameter message
     * @param message 需要返回的错误信息
     * @return Result 对象
     */
    public static Result<?> error(String message) {
        return new Result<>(1, message);
    }



}

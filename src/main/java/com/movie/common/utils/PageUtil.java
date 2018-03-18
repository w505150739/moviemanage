package com.movie.common.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liuyuzhu
 * @description: 分页工具类
 * @date 2018/1/20 1:15
 */
@Setter
@Getter
@ToString
public class PageUtil {

    /**
     * 标识，成功：200，失败：其他状态码
     */
    private Integer code;

    /**
     * 消息提示
     */
    private String message;

    /**
     * 数据总量
     */
    private Integer total;

    /**
     * 数据
     */
    private Object data;

    public PageUtil(Integer code, String message, Integer total, Object data) {
        this.code = code;
        this.message = message;
        this.total = total;
        this.data = data;
    }
    public PageUtil(Integer total,Object data){
        this.code = 200;
        this.message = "成功";
        this.total = total;
        this.data = data;
    }
}

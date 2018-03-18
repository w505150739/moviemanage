package com.movie.common.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ResultData {
    /**
     * 标识，成功：true，失败：false
     */
    private Integer result = 0;

    /**
     * 消息提示
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    public ResultData(){
    }

    public ResultData(Object data){
        this.data = data;
    }

    public ResultData(Integer result,String message){
        this.result = result;
        this.message = message;
    }

    public ResultData successResult(String message,Object data){
        ResultData result = new ResultData();
        result.setResult(0);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public ResultData errorResult(String message){
        ResultData result = new ResultData();
        result.setResult(-1);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}

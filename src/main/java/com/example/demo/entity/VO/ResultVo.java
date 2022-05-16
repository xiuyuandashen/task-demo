package com.example.demo.entity.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class ResultVo implements Serializable {
    private Integer code;
    private Boolean success;
    private String message;
    private HashMap<String,Object> data = new HashMap<>();

    public static ResultVo SUCCESS(){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setMessage(null);
        resultVo.setCode(200);
        return resultVo;
    }

    public static ResultVo SUCCESS(String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setMessage(message);
        resultVo.setCode(200);
        return resultVo;
    }

    public  ResultVo data(String key,Object val){
        this.data.put(key,val);
        return this;
    }

    public ResultVo message(String msg){
        this.setMessage(msg);
        return this;
    }

    public static ResultVo ERROR(){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setMessage(null);
        resultVo.setCode(201);
        return resultVo;
    }

    public static ResultVo ERROR(String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setMessage(message);
        resultVo.setCode(201);
        return resultVo;
    }
}


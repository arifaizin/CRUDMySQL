package net.krearive.crudmysql.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idn on 5/22/2018.
 */

public class ResponseModel {
    @SerializedName("msg")
    private String msg;

    @SerializedName("result")
    private String result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

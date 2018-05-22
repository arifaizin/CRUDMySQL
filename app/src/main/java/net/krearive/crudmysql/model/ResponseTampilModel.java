package net.krearive.crudmysql.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by idn on 5/22/2018.
 */

public class ResponseTampilModel {
    @SerializedName("data")
    private ArrayList<NoteModel> data = new ArrayList<>();

    @SerializedName("msg")
    private String msg;

    @SerializedName("result")
    private String result;

    public ArrayList<NoteModel> getData() {
        return data;
    }

    public void setData(ArrayList<NoteModel> data) {
        this.data = data;
    }

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

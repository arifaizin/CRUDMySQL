package net.krearive.crudmysql.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idn on 5/22/2018.
 */

public class NoteModel {
    @SerializedName("id")
    private String id;

    @SerializedName("pengeluaran")
    private String pengeluaran;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("tanggal")
    private String tanggal;

    //constructor
    public NoteModel(String id, String pengeluaran, String keterangan, String tanggal) {
        this.id = id;
        this.pengeluaran = pengeluaran;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }

    public NoteModel() {
    }

    //setter getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPengeluaran() {
        return pengeluaran;
    }

    public void setPengeluaran(String pengeluaran) {
        this.pengeluaran = pengeluaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}

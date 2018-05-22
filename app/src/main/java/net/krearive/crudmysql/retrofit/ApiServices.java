package net.krearive.crudmysql.retrofit;

import net.krearive.crudmysql.model.ResponseModel;
import net.krearive.crudmysql.model.ResponseTampilModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by idn on 5/22/2018.
 */

public interface ApiServices {
    @GET("show_cash_note.php")
    Call<ResponseTampilModel> tampilDataCatatan();

    @FormUrlEncoded
    @POST("add_note.php")
    Call<ResponseModel> tambahDataCatatan(
          @Field("pengeluaran") String pengeluaran,
          @Field("keterangan") String keterangan,
          @Field("tanggal") String tanggal
    );

    @FormUrlEncoded
    @POST("update_note.php")
    Call<ResponseModel> editDataCatatan(
            @Field("id") String id,
            @Field("pengeluaran") String pengeluaran,
            @Field("keterangan") String keterangan,
            @Field("tanggal") String tanggal
    );

    @FormUrlEncoded
    @POST("delete_note.php")
    Call<ResponseModel> hapusDataCatatan(
            @Field("id") String id
    );



}

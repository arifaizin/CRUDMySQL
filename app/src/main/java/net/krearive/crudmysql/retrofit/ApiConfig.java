package net.krearive.crudmysql.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by idn on 5/22/2018.
 */

public class ApiConfig {
    public static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.97.19/cashnote/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static ApiServices getApiServices(){
        ApiServices service = getRetrofit().create(ApiServices.class);
        return service;
    }
}

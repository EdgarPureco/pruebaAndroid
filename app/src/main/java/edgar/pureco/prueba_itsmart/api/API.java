package edgar.pureco.prueba_itsmart.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    public static Retrofit retrofit;

    // Creacion de retrofit
    public static Retrofit getClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://practicaits2022-production.up.railway.app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}

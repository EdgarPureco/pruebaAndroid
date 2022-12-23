package edgar.pureco.prueba_itsmart.api;

import java.util.ArrayList;

import edgar.pureco.prueba_itsmart.models.Municipio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MunicipioAPI {
    //    Municipios
    @GET("api/municipios/{municipio}")
    Call<Municipio> getMunicipio(@Path("municipio") String municipio);

    @GET("/api/municipios/byEstado/{estado}")
    Call<ArrayList<Municipio>> getMunicipioByEstado(@Path("estado") String estado);

}

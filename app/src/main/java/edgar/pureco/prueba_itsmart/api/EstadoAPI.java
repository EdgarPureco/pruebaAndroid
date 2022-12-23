package edgar.pureco.prueba_itsmart.api;

import edgar.pureco.prueba_itsmart.models.Estado;
import edgar.pureco.prueba_itsmart.models.EstadoList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EstadoAPI {
    //    Estados
    @GET("api/estados")
    Call<EstadoList> getAllEstados();

    @GET("api/estados/{estado}")
    Call<Estado> getEstado(@Path("estado") String estado);
}

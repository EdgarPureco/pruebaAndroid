package edgar.pureco.prueba_itsmart.api;

import edgar.pureco.prueba_itsmart.models.ClienteList;
import edgar.pureco.prueba_itsmart.models.ClienteModel;
import edgar.pureco.prueba_itsmart.models.Estado;
import edgar.pureco.prueba_itsmart.models.Municipio;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteAPI {

//  Cliente
    @GET("api/clientes")
    Call<ClienteList> getClientes();

//  Crear
    @POST("api/clientes")
    Call<ClienteModel> guardarCliente(@Body ClienteModel cliente);

//  Eliminar
    @DELETE("api/clientes/{cliente}")
    Call<ResponseBody> deleteCliente(@Path("cliente") String cliente);

//  Actualizar
    @PUT("api/clientes/{id}")
    Call<ResponseBody> actualizarCliente(@Path("id") String id, @Body ClienteModel cliente);


//    Estados
    @GET("api/estados/{estado}")
    Call<Estado> getEstado(@Path("estado") String estado);

//    Municipios
    @GET("api/municipios/{municipio}")
    Call<Municipio> getMunicipio(@Path("municipio") String municipio);


}

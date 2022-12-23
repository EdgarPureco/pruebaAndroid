package edgar.pureco.prueba_itsmart.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import edgar.pureco.prueba_itsmart.R;
import edgar.pureco.prueba_itsmart.api.API;
import edgar.pureco.prueba_itsmart.api.ClienteAPI;
import edgar.pureco.prueba_itsmart.api.EstadoAPI;
import edgar.pureco.prueba_itsmart.api.MunicipioAPI;
import edgar.pureco.prueba_itsmart.models.Cliente;
import edgar.pureco.prueba_itsmart.models.Estado;
import edgar.pureco.prueba_itsmart.models.Municipio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cliente);

//        Obtencion del cliente desde la Actividad prinicpal
        Bundle extras = getIntent().getExtras();
        Cliente cliente = extras.getParcelable("cliente");

        TextInputEditText txtIdCliente = findViewById(R.id.txtIdCliente);
        TextInputEditText txtNombre = findViewById(R.id.txtNombre);
        TextInputEditText txtApellidoP = findViewById(R.id.txtApellidoP);
        TextInputEditText txtApellidoM = findViewById(R.id.txtApellidoM);
        TextInputEditText txtTelefono = findViewById(R.id.txtTelefono);
        TextInputEditText txtPuesto = findViewById(R.id.txtPuesto);
        TextInputEditText txtSucursal = findViewById(R.id.txtSucursal);
        TextInputEditText txtRfc = findViewById(R.id.txtRfc);
        TextInputEditText txtNombreFiscal = findViewById(R.id.txtNombreFiscal);
        TextInputEditText txtLatitud = findViewById(R.id.txtLatitud);
        TextInputEditText txtLongitud = findViewById(R.id.txtLongitud);
        TextInputEditText txtEstado = findViewById(R.id.txtEstado);
        TextInputEditText txtMunicipio = findViewById(R.id.txtMunicipio);
        TextInputEditText txtCodPost = findViewById(R.id.txtCodPost);
        TextInputEditText txtColonia = findViewById(R.id.txtColonia);
        TextInputEditText txtReferencia = findViewById(R.id.txtReferencia);

        String idEstado = cliente.getFkEstado().toString();
        String idMunicipio = cliente.getFkMunicipio().toString();

//        Obtencion del nombre del estado por medio del ID
        Call<Estado> callEstado = API.getClient().create(EstadoAPI.class).getEstado(idEstado);
        callEstado.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                if (idEstado != null){
                    txtEstado.setText(response.body().getNombreEstado());
                }else{
                    Toast.makeText(InfoCliente.this, "Estado faltante", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {

            }
        });

        //        Obtencion del nombre del municipio por medio del ID
        Call<Municipio> callMunicipio = API.getClient().create(MunicipioAPI.class).getMunicipio(idMunicipio);
        callMunicipio.enqueue(new Callback<Municipio>() {
            @Override
            public void onResponse(Call<Municipio> call, Response<Municipio> response) {
                if (idMunicipio != null){
                    txtMunicipio.setText(response.body().getNombreMunicipio());
                }else{
                    Toast.makeText(InfoCliente.this, "Minicipio faltante", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Municipio> call, Throwable t) {

            }
        });

//        Asignando los valores a los inputs
        txtIdCliente.setText(cliente.getId().toString());
        txtNombre.setText(cliente.getNombre());
        txtApellidoP.setText(cliente.getApellidoP());
        txtApellidoM.setText(cliente.getApellidoM());
        txtTelefono.setText(cliente.getTelefono());
        txtPuesto.setText(cliente.getPuesto());
        txtSucursal.setText(cliente.getSucursal());
        txtRfc.setText(cliente.getRfc());
        txtNombreFiscal.setText(cliente.getNombreFiscal());
        txtLatitud.setText(cliente.getLatitud());
        txtLongitud.setText(cliente.getLongitud());
        txtCodPost.setText(cliente.getCodigoPostal());
        txtColonia.setText(cliente.getColonia());
        txtReferencia.setText(cliente.getReferencia());
    }




}
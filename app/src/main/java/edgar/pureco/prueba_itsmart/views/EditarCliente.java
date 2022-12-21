package edgar.pureco.prueba_itsmart.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import edgar.pureco.prueba_itsmart.MainActivity;
import edgar.pureco.prueba_itsmart.R;
import edgar.pureco.prueba_itsmart.api.API;
import edgar.pureco.prueba_itsmart.api.ClienteAPI;
import edgar.pureco.prueba_itsmart.models.Cliente;
import edgar.pureco.prueba_itsmart.models.ClienteModel;
import edgar.pureco.prueba_itsmart.models.Estado;
import edgar.pureco.prueba_itsmart.models.Municipio;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarCliente extends AppCompatActivity {

    private TextInputEditText txtNombre;
    private TextInputEditText txtApellidoP;
    private TextInputEditText txtApellidoM;
    private TextInputEditText txtTelefono;
    private TextInputEditText txtPuesto;
    private TextInputEditText txtSucursal;
    private TextInputEditText txtRfc;
    private TextInputEditText txtNombreFiscal;
    private TextInputEditText txtLatitud;
    private TextInputEditText txtLongitud;
    private TextInputEditText txtEstado;
    private TextInputEditText txtMunicipio;
    private TextInputEditText txtCodPost;
    private TextInputEditText txtColonia;
    private TextInputEditText txtReferencia;
    private FloatingActionButton btnGuardar;
    String idCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        //        Obtencion del cliente desde la Actividad prinicpal
        Bundle extras = getIntent().getExtras();
        Cliente cliente = extras.getParcelable("cliente");

        TextInputEditText txtIdCliente = findViewById(R.id.txtIdCliente);
         txtNombre = findViewById(R.id.txtNombre);
         txtApellidoP = findViewById(R.id.txtApellidoP);
         txtApellidoM = findViewById(R.id.txtApellidoM);
         txtTelefono = findViewById(R.id.txtTelefono);
         txtPuesto = findViewById(R.id.txtPuesto);
         txtSucursal = findViewById(R.id.txtSucursal);
         txtRfc = findViewById(R.id.txtRfc);
         txtNombreFiscal = findViewById(R.id.txtNombreFiscal);
         txtLatitud = findViewById(R.id.txtLatitud);
         txtLongitud = findViewById(R.id.txtLongitud);
         txtEstado = findViewById(R.id.txtEstado);
         txtMunicipio = findViewById(R.id.txtMunicipio);
         txtCodPost = findViewById(R.id.txtCodPost);
         txtColonia = findViewById(R.id.txtColonia);
         txtReferencia = findViewById(R.id.txtReferencia);

        String idEstado = cliente.getFkEstado().toString();
        String idMunicipio = cliente.getFkMunicipio().toString();

        //        Obtencion del nombre del estado por medio del ID
        Call<Estado> callEstado = API.getClient().create(ClienteAPI.class).getEstado(idEstado);
        callEstado.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                if (idEstado != null){
                    txtEstado.setText(response.body().getNombreEstado());
                }else{
                    Toast.makeText(EditarCliente.this, "Estado faltante", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {

            }
        });

        //        Obtencion del nombre del municipio por medio del ID
        Call<Municipio> callMunicipio = API.getClient().create(ClienteAPI.class).getMunicipio(idMunicipio);
        callMunicipio.enqueue(new Callback<Municipio>() {
            @Override
            public void onResponse(Call<Municipio> call, Response<Municipio> response) {
                if (idMunicipio != null){
                    txtMunicipio.setText(response.body().getNombreMunicipio());
                }else{
                    Toast.makeText(EditarCliente.this, "Minicipio faltante", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Municipio> call, Throwable t) {

            }
        });

        idCliente = cliente.getId().toString();
        txtIdCliente.setText(idCliente);
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

        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaciones(idCliente);
            }
        });
    }

    public void guardarCambios(String id, ClienteModel cliente){

        Call<ResponseBody> call = API.getClient().create(ClienteAPI.class).actualizarCliente(id, cliente);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(EditarCliente.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(EditarCliente.this, "Cambios Guardados", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditarCliente.this, "Error al guardar, intente de nuevo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditarCliente.this, "Error al guardar, intente de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validaciones(String id){
        String nombre = txtNombre.getText().toString();
        String aP = txtApellidoP.getText().toString();
        String aM = txtApellidoM.getText().toString();
        String tel = txtTelefono.getText().toString();
        String puesto = txtPuesto.getText().toString();
        String sucursal = txtSucursal.getText().toString();
        String rfc = txtRfc.getText().toString();
        String nombreF = txtNombreFiscal.getText().toString();
        String lat = txtLatitud.getText().toString();
        String lon = txtLongitud.getText().toString();
        String est = txtEstado.getText().toString();
        String mun = txtMunicipio.getText().toString();
        String cP = txtCodPost.getText().toString();
        String col = txtColonia.getText().toString();
        String ref = txtReferencia.getText().toString();
        Integer estado;
        Integer municipio;

        if(nombre.length()==0)
        {
            txtNombre.requestFocus();
            txtNombre.setError("Campo Obligatorio");
        }
        else if(!nombre.matches("[a-zA-Z ]+"))
        {
            txtNombre.requestFocus();
            txtNombre.setError("Solo se admiten letras");
        }

        else if(aP.length()==0)
        {
            txtApellidoP.requestFocus();
            txtApellidoP.setError("Campo Obligatorio");
        }
        else if(!aP.matches("[a-zA-Z ]+"))
        {
            txtApellidoP.requestFocus();
            txtApellidoP.setError("Solo se admiten letras");
        }

        else if(aM.length()==0)
        {
            txtApellidoM.requestFocus();
            txtApellidoM.setError("Campo Obligatorio");
        }
        else if(!aM.matches("[a-zA-Z ]+"))
        {
            txtApellidoM.requestFocus();
            txtApellidoM.setError("Solo se admiten letras");
        }

        else if(tel.length()<10)
        {
            txtTelefono.requestFocus();
            txtTelefono.setError("Campo Obligatorio, requiere 10 carácteres");
        }

        else if(puesto.length()==0)
        {
            txtPuesto.requestFocus();
            txtPuesto.setError("Campo Obligatorio");
        }
        else if(!puesto.matches("[a-zA-Z ]+"))
        {
            txtApellidoM.requestFocus();
            txtApellidoM.setError("Solo se admiten letras");
        }

        else if(sucursal.length()==0)
        {
            txtSucursal.requestFocus();
            txtSucursal.setError("Campo Obligatorio");
        }else if(!sucursal.matches("[a-zA-Z ]+"))
        {
            txtSucursal.requestFocus();
            txtSucursal.setError("Solo se admiten letras");
        }

        else if(rfc.length()<13)
        {
            txtRfc.requestFocus();
            txtRfc.setError("Campo Obligatorio, requiere 13 carácteres");
        }
        else if(!rfc.matches("^[a-zA-Z0-9_.-]*$"))
        {
            txtRfc.requestFocus();
            txtRfc.setError("Solo se admiten letras y numeros");
        }

        else if(nombreF.length()==0)
        {
            txtNombreFiscal.requestFocus();
            txtNombreFiscal.setError("Campo Obligatorio");
        }
        else if(!nombreF.matches("[a-zA-Z ]+"))
        {
            txtNombreFiscal.requestFocus();
            txtNombreFiscal.setError("Solo se admiten letras");
        }

        else if(lat.length()==0)
        {
            txtLatitud.requestFocus();
            txtLatitud.setError("Campo Obligatorio");
        }else if(lat.matches("[a-zA-Z ]+"))
        {
            txtLatitud.requestFocus();
            txtLatitud.setError("No se admiten letras");
        }

        else if(lon.length()==0)
        {
            txtLongitud.requestFocus();
            txtLongitud.setError("Campo Obligatorio");
        }else if(lon.matches("[a-zA-Z ]+"))
        {
            txtLongitud.requestFocus();
            txtLongitud.setError("No se admiten letras");
        }


        else if(est.length()==0)
        {
            txtEstado.requestFocus();
            txtEstado.setError("Campo Obligatorio");
        }else if(est.matches("[a-zA-Z ]+"))
        {
            txtEstado.requestFocus();
            txtEstado.setError("No se admiten letras");
        }

        else if(mun.length()==0)
        {
            txtMunicipio.requestFocus();
            txtMunicipio.setError("Campo Obligatorio");
        }else if(mun.matches("[a-zA-Z ]+"))
        {
            txtMunicipio.requestFocus();
            txtMunicipio.setError("No se admiten letras");
        }


        else if(cP.length()<5)
        {
            txtCodPost.requestFocus();
            txtCodPost.setError("Campo obligatorio, requiere 5 carácteres");
        }else if(cP.matches("[a-zA-Z ]+"))
        {
            txtCodPost.requestFocus();
            txtCodPost.setError("No se admiten letras");
        }


        else if(col.length()==0)
        {
            txtColonia.requestFocus();
            txtColonia.setError("Campo Obligatorio");
        }

        else if(ref.length()==0)
        {
            txtReferencia.requestFocus();
            txtReferencia.setError("Campo Obligatorio");
        }else{
            estado = Integer.valueOf(txtEstado.getText().toString());
            municipio = Integer.valueOf(txtMunicipio.getText().toString());
            ClienteModel cliente = new ClienteModel(nombre, aP, aM, tel, puesto, sucursal, rfc, nombreF, lat, lon, estado, municipio, cP, col, ref);
            guardarCambios(id, cliente);
        }


    }



}

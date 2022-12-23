package edgar.pureco.prueba_itsmart.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edgar.pureco.prueba_itsmart.MainActivity;
import edgar.pureco.prueba_itsmart.R;
import edgar.pureco.prueba_itsmart.api.API;
import edgar.pureco.prueba_itsmart.api.ClienteAPI;
import edgar.pureco.prueba_itsmart.api.EstadoAPI;
import edgar.pureco.prueba_itsmart.api.MunicipioAPI;
import edgar.pureco.prueba_itsmart.models.ClienteModel;
import edgar.pureco.prueba_itsmart.models.Estado;
import edgar.pureco.prueba_itsmart.models.EstadoList;
import edgar.pureco.prueba_itsmart.models.Municipio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearCliente extends AppCompatActivity {

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
   private MaterialAutoCompleteTextView txtEstado;
   private MaterialAutoCompleteTextView txtMunicipio;
   private TextInputEditText txtCodPost;
   private TextInputEditText txtColonia;
   private TextInputEditText txtReferencia;
   private FloatingActionButton btnGuardar;
   private Integer idEstado;
   private Integer idMunicipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cliente);

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

         btnGuardar = findViewById(R.id.btnGuardar);
         btnGuardar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 validaciones();
             }
         });

//         Obtener todos los estados para generarlos en el input
        Call<EstadoList> call = API.getClient().create(EstadoAPI.class).getAllEstados();
        call.enqueue(new Callback<EstadoList>() {
            @Override
            public void onResponse(Call<EstadoList> call, Response<EstadoList> response) {

                if(response.isSuccessful()){
                    List<String> listEstados = new ArrayList<String>();
                    ArrayList<Estado> estados = response.body().getEstadosList();
                    estados.forEach(estado -> listEstados.add(estado.getNombreEstado()));

                    String[] arrayEstados = listEstados.toArray(new String[0]);

                    txtEstado.setSimpleItems(arrayEstados);

//                    Funcion al seleccionar un Estado
                    txtEstado.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                            idEstado = estados.get(position).getId();

//                            Cargar Municipios del Estado seleccionado
                            Call<ArrayList<Municipio>> call = API.getClient().create(MunicipioAPI.class).getMunicipioByEstado(idEstado.toString());
                            call.enqueue(new Callback<ArrayList<Municipio>>() {
                                @Override
                                public void onResponse(Call<ArrayList<Municipio>> call, Response<ArrayList<Municipio>> response) {
                                    List<String> list = new ArrayList<String>();
                                    ArrayList<Municipio> municipios = response.body();
                                    municipios.forEach(municipio -> list.add(municipio.getNombreMunicipio()));
                                    String[] arrayMunicipios = list.toArray(new String[0]);
                                    txtMunicipio.setSimpleItems(arrayMunicipios);

//                                    Funcion al seleccionar un Municipio
                                    txtMunicipio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                                            idMunicipio = municipios.get(i).getId();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<ArrayList<Municipio>> call, Throwable t) {
                                    Log.d("Municipios", "onResponse: " + t.getLocalizedMessage());
                                    Log.d("Municipios", "onResponse: " + t.getMessage());
                                    Log.d("Municipios", "onResponse: " + t.getCause());
                                }
                            });
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<EstadoList> call, Throwable t) {
                Log.d("Estados", "onResponse: " + t.getLocalizedMessage());
                Log.d("Estados", "onResponse: " + t.getMessage());
                Log.d("Estados", "onResponse: " + t.getCause());
            }
        });

    }


    public void guardarCliente(ClienteModel cliente){

        btnGuardar.setEnabled(false);
        Call<ClienteModel> call = API.getClient().create(ClienteAPI.class).guardarCliente(cliente);
        call.enqueue(new Callback<ClienteModel>() {
            @Override
            public void onResponse(Call<ClienteModel> call, Response<ClienteModel> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(CrearCliente.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(CrearCliente.this, "Cliente Guardado", Toast.LENGTH_SHORT).show();
                    btnGuardar.setEnabled(true);
                }else{
                    Toast.makeText(CrearCliente.this, "Error al guardar, intente de nuevo", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClienteModel> call, Throwable t) {
                Toast.makeText(CrearCliente.this, "Error al guardar, intente de nuevo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validaciones(){
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
        String cP = txtCodPost.getText().toString();
        String col = txtColonia.getText().toString();
        String ref = txtReferencia.getText().toString();

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


        else if(idEstado==null)
        {
            txtEstado.requestFocus();
            txtEstado.setError("Campo Obligatorio");
        }

        else if(idMunicipio==null)
        {
            txtMunicipio.requestFocus();
            txtMunicipio.setError("Campo Obligatorio");
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

            ClienteModel cliente = new ClienteModel(nombre, aP, aM, tel, puesto, sucursal, rfc, nombreF, lat, lon, idEstado, idMunicipio, cP, col, ref);
            guardarCliente(cliente);
        }


    }


}

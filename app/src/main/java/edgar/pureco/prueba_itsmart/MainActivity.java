package edgar.pureco.prueba_itsmart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import edgar.pureco.prueba_itsmart.api.API;
import edgar.pureco.prueba_itsmart.api.ClienteAPI;
import edgar.pureco.prueba_itsmart.adapters.AdapterCliente;
import edgar.pureco.prueba_itsmart.models.Cliente;
import edgar.pureco.prueba_itsmart.models.ClienteList;
import edgar.pureco.prueba_itsmart.views.InfoCliente;
import edgar.pureco.prueba_itsmart.views.CrearCliente;
import edgar.pureco.prueba_itsmart.views.EditarCliente;
import edgar.pureco.prueba_itsmart.views.Modal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ClienteList clientes;
    private AdapterCliente adapterCliente;
    private RecyclerView listaClientes;
    private LinearLayoutManager linearManager;
    private FloatingActionButton btnNuevo;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaClientes = findViewById(R.id.listaClientes);
        linearManager = new LinearLayoutManager(this);
        listaClientes.setLayoutManager(linearManager);

        // Barra de progreso
        loadingBar = new ProgressDialog(MainActivity.this);
        loadingBar.setMax(100);
        loadingBar.setMessage("Cargando....");
        loadingBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        loadingBar.show();

//        Metodo para obtener los clientes
        cargarClientes();

//      Boton para agregar un nuevo cliente
        btnNuevo = findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearCliente.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Nuevo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void cargarClientes(){
//      Fucionalidad de los cards y la obtencion de los clientes
        Call<ClienteList> call = API.getClient().create(ClienteAPI.class).getClientes();
        call.enqueue(new Callback<ClienteList>() {
            @Override
            public void onResponse(Call<ClienteList> call, Response<ClienteList> response) {
                if (response.isSuccessful()){
                    clientes = response.body();
                    adapterCliente = new AdapterCliente(getApplicationContext(), clientes);
                    listaClientes.setAdapter(adapterCliente);


//        Funcionalidad al presionar el card
                    adapterCliente.setOnClienteClickListener(new AdapterCliente.OnClienteClickListener() {
                        @Override
                        public void OnClienteClick(Cliente cliente) {
                            Intent intent = new Intent(MainActivity.this, InfoCliente.class);
                            intent.putExtra("cliente", cliente);
                            startActivity(intent);
                        }
                    });

//        Funcionalidad del boton editar
                    adapterCliente.setOnEditarClickListener(new AdapterCliente.OnEditarClickListener() {
                        @Override
                        public void OnEditarClick(Cliente cliente) {
                            Intent intent = new Intent(MainActivity.this, EditarCliente.class);
                            intent.putExtra("cliente", cliente);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Modo edición", Toast.LENGTH_SHORT).show();
                        }
                    });

                    //        Funcionalidad del boton eliminar
                    adapterCliente.setOnEliminarClickListener(new AdapterCliente.OnEliminarClickListener() {
                        @Override
                        public void OnEliminarClick(Cliente cliente) {
                            Modal modal = new Modal();
                            String idCliente = cliente.getId().toString();
                            modal.showModalMaterial(MainActivity.this, idCliente);
                        }
                    });
                }
                loadingBar.dismiss();
            }

            @Override
            public void onFailure(Call<ClienteList> call, Throwable t) {
                Log.d("Hola", "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
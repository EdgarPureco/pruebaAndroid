package edgar.pureco.prueba_itsmart.views;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import edgar.pureco.prueba_itsmart.api.API;
import edgar.pureco.prueba_itsmart.api.ClienteAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modal extends DialogFragment {

    //    Diseño de modal predeterminado
    public void showModal(Context context, String idCliente){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Advertencia")
                .setMessage("¿Estás seguro de eliminar el cliente?")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<ResponseBody> call = API.getClient().create(ClienteAPI.class).deleteCliente(idCliente);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Cliente eliminado", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(context, "Error al eliminar ", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

//    Diseño de modal de MaterialDesign
    public void showModalMaterial(Context context, String idCliente){
        MaterialAlertDialogBuilder modal = new MaterialAlertDialogBuilder(context);
        modal.setTitle("Advertencia")
                .setMessage("¿Estás seguro de eliminar el cliente?")
                .setCancelable(true)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<ResponseBody> call = API.getClient().create(ClienteAPI.class).deleteCliente(idCliente);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Cliente eliminado", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(context, "Error al eliminar ", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    }


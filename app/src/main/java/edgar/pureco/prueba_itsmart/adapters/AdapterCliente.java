package edgar.pureco.prueba_itsmart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edgar.pureco.prueba_itsmart.R;
import edgar.pureco.prueba_itsmart.models.Cliente;
import edgar.pureco.prueba_itsmart.models.ClienteList;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.ViewHolder> {

    private Context context;
    private ClienteList lista;
    private OnClienteClickListener onClienteClickListener;
    private OnEditarClickListener onEditarClickListener;
    private OnEliminarClickListener onEliminarClickListener;

//    Metodos al dar click en el card
    public void setOnClienteClickListener(OnClienteClickListener onClienteClickListener) {
        this.onClienteClickListener = onClienteClickListener;
    }
    public interface OnClienteClickListener{
        void OnClienteClick(Cliente cliente);
    }

//     Metodos para el boton editar
    public void setOnEditarClickListener(OnEditarClickListener onEditarClickListener) {
        this.onEditarClickListener = onEditarClickListener;
    }
    public interface OnEditarClickListener{
        void OnEditarClick(Cliente cliente);
    }

//    Metodos para el boton eliminar
    public void setOnEliminarClickListener(OnEliminarClickListener onEliminarClickListener) {
        this.onEliminarClickListener = onEliminarClickListener;
    }
    public interface OnEliminarClickListener{
        void OnEliminarClick(Cliente cliente);
    }

//    Constructor
    public AdapterCliente(Context context, ClienteList lista) {
        this.context = context;
        this.lista = lista;
    }

//    Creacion del card
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_cliente, parent, false);
        return new ViewHolder(view);
    }

//    Asignacion de los valores al card
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cliente cliente = (Cliente) lista.getClienteList().get(position);

//        Generar solamente los clientes con estatus activo
        if (cliente.getEstatus()!=0){
            holder.rfc.setText(cliente.getRfc());
            String nombreCompleto = cliente.getNombre() + ' ' + cliente.getApellidoP() + ' ' + cliente.getApellidoM();
            holder.nombre.setText(nombreCompleto);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClienteClickListener.OnClienteClick(cliente);
                }
            });

            holder.itemView.findViewById(R.id.btnEditar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEditarClickListener.OnEditarClick(cliente);
                }
            });

            holder.itemView.findViewById(R.id.btnEliminar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEliminarClickListener.OnEliminarClick(cliente);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.getClienteList().size();
    }

//    ViewHolder para obtener los views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public TextView rfc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.name);
            rfc = (TextView) itemView.findViewById(R.id.rfc);
        }
    }
}
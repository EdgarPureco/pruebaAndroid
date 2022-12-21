package edgar.pureco.prueba_itsmart.models;

import java.util.ArrayList;

import edgar.pureco.prueba_itsmart.models.Cliente;

// Clase para poder obtener la respuesta del servicio de clientes
// Es para crear un objeto que contiene una lista de objetos tipo cliente

public class ClienteList {
    private ArrayList<Cliente> clientes;

    public ClienteList(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Cliente> getClienteList() {
        return clientes;
    }
}

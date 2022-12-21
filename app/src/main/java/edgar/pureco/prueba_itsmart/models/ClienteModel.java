package edgar.pureco.prueba_itsmart.models;

// Modelo del cliente para crear uno nuevo

public class ClienteModel {
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String telefono;
    private String puesto;
    private String sucursal;
    private String rfc;
    private String nombreFiscal;
    private String latitud;
    private String longitud;
    private Integer fkEstado;
    private Integer fkMunicipio;
    private String codigoPostal;
    private String colonia;
    private String referencia;

    public ClienteModel(String nombre, String apellidoP, String apellidoM, String telefono, String puesto, String sucursal, String rfc, String nombreFiscal, String latitud, String longitud, Integer fkEstado, Integer fkMunicipio, String codigoPostal, String colonia, String referencia) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.telefono = telefono;
        this.puesto = puesto;
        this.sucursal = sucursal;
        this.rfc = rfc;
        this.nombreFiscal = nombreFiscal;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fkEstado = fkEstado;
        this.fkMunicipio = fkMunicipio;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreFiscal() {
        return nombreFiscal;
    }

    public void setNombreFiscal(String nombreFiscal) {
        this.nombreFiscal = nombreFiscal;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Integer getFkMunicipio() {
        return fkMunicipio;
    }

    public void setFkMunicipio(Integer fkMunicipio) {
        this.fkMunicipio = fkMunicipio;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}

package edgar.pureco.prueba_itsmart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {
    private Integer id;
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
    private Integer estatus;

    public Cliente(Integer id, String nombre, String apellidoP, String apellidoM, String telefono, String puesto, String sucursal, String rfc, String nombreFiscal, String latitud, String longitud, Integer fkEstado, Integer fkMunicipio, String codigoPostal, String colonia, String referencia, Integer estatus) {
        this.id = id;
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
        this.estatus = estatus;
    }

    protected Cliente(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombre = in.readString();
        apellidoP = in.readString();
        apellidoM = in.readString();
        telefono = in.readString();
        puesto = in.readString();
        sucursal = in.readString();
        rfc = in.readString();
        nombreFiscal = in.readString();
        latitud = in.readString();
        longitud = in.readString();
        if (in.readByte() == 0) {
            fkEstado = null;
        } else {
            fkEstado = in.readInt();
        }
        if (in.readByte() == 0) {
            fkMunicipio = null;
        } else {
            fkMunicipio = in.readInt();
        }
        codigoPostal = in.readString();
        colonia = in.readString();
        referencia = in.readString();
        if (in.readByte() == 0) {
            estatus = null;
        } else {
            estatus = in.readInt();
        }
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(nombre);
        parcel.writeString(apellidoP);
        parcel.writeString(apellidoM);
        parcel.writeString(telefono);
        parcel.writeString(puesto);
        parcel.writeString(sucursal);
        parcel.writeString(rfc);
        parcel.writeString(nombreFiscal);
        parcel.writeString(latitud);
        parcel.writeString(longitud);
        if (fkEstado == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fkEstado);
        }
        if (fkMunicipio == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fkMunicipio);
        }
        parcel.writeString(codigoPostal);
        parcel.writeString(colonia);
        parcel.writeString(referencia);
        if (estatus == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(estatus);
        }
    }
}

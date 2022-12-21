package edgar.pureco.prueba_itsmart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Municipio implements Parcelable {
    private Integer id;
    private String nombreMunicipio;
    private Integer fkEstado;

    public Municipio(Integer id, String nombreMunicipio, Integer fkEstado) {
        this.id = id;
        this.nombreMunicipio = nombreMunicipio;
        this.fkEstado = fkEstado;
    }

    protected Municipio(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombreMunicipio = in.readString();
        if (in.readByte() == 0) {
            fkEstado = null;
        } else {
            fkEstado = in.readInt();
        }
    }

    public static final Creator<Municipio> CREATOR = new Creator<Municipio>() {
        @Override
        public Municipio createFromParcel(Parcel in) {
            return new Municipio(in);
        }

        @Override
        public Municipio[] newArray(int size) {
            return new Municipio[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
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
        parcel.writeString(nombreMunicipio);
        if (fkEstado == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(fkEstado);
        }
    }
}

package edgar.pureco.prueba_itsmart.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Estado implements Parcelable {
    private Integer id;
    private String nombreEstado;

    public Estado(Integer id, String nombreEstado) {
        this.id = id;
        this.nombreEstado = nombreEstado;
    }

    protected Estado(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        nombreEstado = in.readString();
    }

    public static final Creator<Estado> CREATOR = new Creator<Estado>() {
        @Override
        public Estado createFromParcel(Parcel in) {
            return new Estado(in);
        }

        @Override
        public Estado[] newArray(int size) {
            return new Estado[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
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
        parcel.writeString(nombreEstado);
    }
}

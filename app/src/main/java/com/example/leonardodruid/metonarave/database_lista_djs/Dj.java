package com.example.leonardodruid.metonarave.database_lista_djs;

import android.os.Parcel;
import android.os.Parcelable;

public class Dj implements Parcelable {

    private String id;
    private String estilo;
    private String urlimagemDj;
    private String nome;

    public Dj(){


    }

    public Dj(String id, String estilo, String urlimagemDj, String nome) {
        this.id = id;
        this.estilo = estilo;
        this.urlimagemDj = urlimagemDj;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getUrlimagemDj() {
        return urlimagemDj;
    }

    public void setUrlimagemDj(String urlimagemDj) {
        this.urlimagemDj = urlimagemDj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.estilo);
        dest.writeString(this.urlimagemDj);
        dest.writeString(this.nome);
    }

    protected Dj(Parcel in) {
        this.id = in.readString();
        this.estilo = in.readString();
        this.urlimagemDj = in.readString();
        this.nome = in.readString();
    }

    public static final Creator<Dj> CREATOR = new Creator<Dj>() {
        @Override
        public Dj createFromParcel(Parcel source) {
            return new Dj(source);
        }

        @Override
        public Dj[] newArray(int size) {
            return new Dj[size];
        }
    };
}



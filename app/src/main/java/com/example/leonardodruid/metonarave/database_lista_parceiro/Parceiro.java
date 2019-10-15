package com.example.leonardodruid.metonarave.database_lista_parceiro;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Parceiro implements Parcelable {

    private String nome;
    private String urlimagem;
    private String id;

    public Parceiro(){


    }


    public Parceiro(String nome, String urlimagem  , String id) {
        this.nome = nome;
        this.urlimagem = urlimagem;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

    public void Urlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeString(this.urlimagem);
        dest.writeString(this.id);
    }

    protected Parceiro(Parcel in) {
        this.nome = in.readString();
        this.urlimagem = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Parceiro> CREATOR = new Creator<Parceiro>() {
        @Override
        public Parceiro createFromParcel(Parcel source) {
            return new Parceiro(source);
        }

        @Override
        public Parceiro[] newArray(int size) {
            return new Parceiro[size];
        }
    };
}

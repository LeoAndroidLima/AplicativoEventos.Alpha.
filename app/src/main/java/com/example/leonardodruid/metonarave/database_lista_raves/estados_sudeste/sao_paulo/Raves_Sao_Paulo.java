package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.sao_paulo;

import android.os.Parcel;
import android.os.Parcelable;

public class Raves_Sao_Paulo implements Parcelable {

    private String id;
    private String nome;
    private String localizacao;
    private String urlimagem;
    private String data;


    public Raves_Sao_Paulo(){


    }

    public Raves_Sao_Paulo(String id, String nome, String localizacao, String urlimagem, String data) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.urlimagem = urlimagem;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getUrlimagem() {
        return urlimagem;
    }

    public void setUrlimagem(String urlimagem) {
        this.urlimagem = urlimagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.localizacao);
        dest.writeString(this.urlimagem);
        dest.writeString(this.data);
    }

    protected Raves_Sao_Paulo(Parcel in) {
        this.id = in.readString();
        this.nome = in.readString();
        this.localizacao = in.readString();
        this.urlimagem = in.readString();
        this.data = in.readString();
    }

    public static final Creator<Raves_Sao_Paulo> CREATOR = new Creator<Raves_Sao_Paulo>() {
        @Override
        public Raves_Sao_Paulo createFromParcel(Parcel source) {
            return new Raves_Sao_Paulo(source);
        }

        @Override
        public Raves_Sao_Paulo[] newArray(int size) {
            return new Raves_Sao_Paulo[size];
        }
    };
}

package com.example.leonardodruid.metonarave;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class TratamentoDeErros {

    public static boolean VerificarInternet(Context context){

        ConnectivityManager conexao = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo informacao = conexao.getActiveNetworkInfo();

        if (informacao != null && informacao.isConnected()){

            return true;

        }else{

            return false;

        }
    }

    public static void opcoesErros(Context context, String resposta){

        if (resposta.contains("Password should be at least 6 characters")){

            Toast.makeText(context, "Digite uma senha maior que 5 Characteres",
                    Toast.LENGTH_LONG).show();

        }else if (resposta.contains("The email address is badly formatted")){

            Toast.makeText(context, "E-mail inválido", Toast.LENGTH_LONG).show();

        }else if (resposta.contains("The email address is already in use by another account")){

            Toast.makeText(context, "O E-mail Cadastrado já existe", Toast.LENGTH_LONG).show();

        }else if (resposta.contains("There is no user record corresponding to this identifier.")){

            Toast.makeText(context, "O E-mail Inválido", Toast.LENGTH_LONG).show();

        }else if (resposta.contains("interrupted connection")) {

            Toast.makeText(context, "Sua conexão não autoriza o acesso", Toast.LENGTH_LONG).show();

        }else if (resposta.contains("12501:")) {

            Toast.makeText(context, "Cancelado", Toast.LENGTH_LONG).show();


    }else{

            Toast.makeText(context,resposta,Toast.LENGTH_LONG).show();

        }
    }
}

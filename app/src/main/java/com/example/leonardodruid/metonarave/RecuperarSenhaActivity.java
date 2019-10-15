package com.example.leonardodruid.metonarave;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarSenhaActivity extends AppCompatActivity {

    private CardView cardView_ContinuarRecuperarSenha;
    private EditText editText_RecuperarSenha;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        auth = FirebaseAuth.getInstance();

        cardView_ContinuarRecuperarSenha = (CardView)findViewById(R.id.cardView_ContinuarRecuperarSenha);
        editText_RecuperarSenha = (EditText)findViewById(R.id.editText_RecuperarSenha);

        //-----------------------BOTAO DE VOLTAR-------------------------------
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_voltar_preto_24dp);


        cardView_ContinuarRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = editText_RecuperarSenha.getText().toString().trim();

                if (email.isEmpty()){

                    Toast.makeText(getBaseContext(), "Insira seu e-mail para Recuperar sua senha",
                            Toast.LENGTH_LONG).show();

                }else {

                    enviarEmail(email);

                }

            }
        });

    }

    private void enviarEmail(String email){

        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getBaseContext(), "Enviamos um e-mail com um link para você redefinir sua senha",
                        Toast.LENGTH_LONG).show();

                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                String erro = e.toString();

                TratamentoDeErros.opcoesErros(getBaseContext(),erro);

            }
        });


    }

    //-----------------------------BOTÃO DE VOLTAR----------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }
}

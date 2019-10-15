package com.example.leonardodruid.metonarave;

import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarEmailActivity extends AppCompatActivity {

    private EditText editText_CadastrarEmail, editText_CadastrarSenha, editText_CadastrarRepetirSenha;
    private CardView cardView_TestarCadastrar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_email);

        //--------------------------------------Botão de voltar-------------------------
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_voltar_preto_24dp);

        //-----------------------------------Buttons-------------------------------------
        cardView_TestarCadastrar = (CardView) findViewById(R.id.cardView_TestarCadastro);

        //------------------------------------EditText-----------------------------------
        editText_CadastrarEmail = (EditText) findViewById(R.id.editText_CadastrarEmail);
        editText_CadastrarSenha = (EditText) findViewById(R.id.editText_CadastrarSenha);
        editText_CadastrarRepetirSenha = (EditText) findViewById(R.id.editText_CadastrarRepetirSenha);

        auth = FirebaseAuth.getInstance();

        //------------------------------------Eventos de Clicks---------------------------
        cardView_TestarCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth = FirebaseAuth.getInstance();

                String email = editText_CadastrarEmail.getText().toString().trim();
                String senha = editText_CadastrarSenha.getText().toString().trim();
                String confirmarSenha = editText_CadastrarRepetirSenha.getText().toString().trim();

                if (email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {

                    Toast.makeText(getBaseContext(), "Preencha os campos",
                            Toast.LENGTH_LONG).show();

                }else {

                    //Verificando possiveis erros
                    if (senha.contentEquals(confirmarSenha)) {

                        if (TratamentoDeErros.VerificarInternet(getBaseContext())) {

                            ConnectivityManager conexao = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                            //Criando metodo criar usuario
                            criarUsuario(email, senha);

                        } else {

                            Toast.makeText(getBaseContext(), "Verifique sua internet se esta ligada",
                                    Toast.LENGTH_LONG).show();
                        }

                    }else{

                        Toast.makeText(getBaseContext(),"Senhas Diferentes",
                                Toast.LENGTH_LONG).show();
                    }

                    }
                }

            private void criarUsuario (String email, String senha) {

                auth.createUserWithEmailAndPassword(email,senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    Toast.makeText(getBaseContext(),"Cadastrado com Sucesso",
                                            Toast.LENGTH_LONG).show();

                                    finish();

                                }else {

                                    String resposta = task.getException().toString();
                                    TratamentoDeErros.opcoesErros(getBaseContext(), resposta);
                                }
                            }
                        });
            }

        });

    }

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




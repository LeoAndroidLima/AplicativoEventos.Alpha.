package com.example.leonardodruid.metonarave;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView textView_EsqueceuSenha;
    private CardView cardView_EmailLogin, cardView_FacebookLogin, cardView_GoogleLogin, cardView_AnonimoLogin, cardView_CadastrarEmail;
    private EditText editText_LoginEmail, editText_SenhaEmail;

    private FirebaseAuth auth;
    private FirebaseUser user;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth.AuthStateListener authStateListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbaredit);




        auth = FirebaseAuth.getInstance();

        //Verificar se o usuario está logado
        servicosAuthenticacao();

        //Entrar com o Google
        servicosGoogle();

        //Entrar com o Facebook
        servicosFacebook();

        //Logar se o usuario estiver Logado
       // user = auth.getCurrentUser();

        //---------------------------Buttons---------------------------------------
        textView_EsqueceuSenha = (TextView) findViewById(R.id.textView_EsquecerSenha);
        cardView_EmailLogin = (CardView) findViewById(R.id.cardView_EmailLogin);
        cardView_FacebookLogin = (CardView) findViewById(R.id.cardView_FacebookLogin);
        cardView_GoogleLogin = (CardView) findViewById(R.id.cardView_GoogleLogin);
        cardView_AnonimoLogin = (CardView) findViewById(R.id.cardView_AnonimoLogin);
        cardView_CadastrarEmail = (CardView) findViewById(R.id.cardView_CadastrarEmail);

        //------------------------------EditText---------------------------------------
        editText_LoginEmail = (EditText) findViewById(R.id.editText_LoginEmail);
        editText_SenhaEmail = (EditText) findViewById(R.id.editText_SenhaEmail);

        //-------------------------------Tratamento de Clicks-------------------------------------
        textView_EsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_RecuperarSenha = new Intent(getApplicationContext(), RecuperarSenhaActivity.class);
                startActivity(intent_RecuperarSenha);

            }
        });

        cardView_CadastrarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_CadastrarEmail = new Intent(getApplicationContext(), CadastrarEmailActivity.class);
                startActivity(intent_CadastrarEmail);

            }
        });

        cardView_EmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignInEmailLogin();

            }
        });

        cardView_GoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignInGoogle();

            }
        });

        cardView_FacebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInFacebook();

            }
        });

        cardView_AnonimoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInAnonimo();

            }
        });

    }
    //---------------------------------------Metodos De Login----------------------------------------------

    private void SignInEmailLogin(){

        String email = editText_LoginEmail.getText().toString().trim();
        String senha = editText_SenhaEmail.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()){

            Toast.makeText(getBaseContext(), "Insira o E-mail ou Senha",
                    Toast.LENGTH_LONG).show();

        }else{

            if (TratamentoDeErros.VerificarInternet(this)){

                ConnectivityManager conexao = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                servicosEmailSenha(email,senha);

            }else{

                Toast.makeText(getBaseContext(), "Algo deu Errado! Verifique se o WIFI ou a Internet do aparelho",
                        Toast.LENGTH_LONG).show();

            }
        }

    }

    private void SignInGoogle(){

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account == null){

            Intent intent = googleSignInClient.getSignInIntent();

            startActivityForResult(intent, 555);

        }else {

            startActivity(new Intent(getBaseContext(),TelaPrincipalActivity.class));

            finish();

        }

    }

    private void signInFacebook(){

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

    }

    private void signInAnonimo(){

        AdicionarContaAnonimaAoFirebase();

    }

    //------------------------------Serviços de Login--------------------------------

    private void servicosEmailSenha(final String email, String senha) {

        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    startActivity(new Intent(getBaseContext(),TelaPrincipalActivity.class));
                    Toast.makeText(getBaseContext(),   email + " Logado com Sucesso! ",
                            Toast.LENGTH_LONG).show();

                    finish();

                }else{

                    Toast.makeText(getBaseContext(), "E-mail ou senha incorretor",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void servicosAuthenticacao(){

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){


                }else {


                }
            }
        };

    }

    private void servicosGoogle(){


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             .requestIdToken(getString(R.string.default_web_client_id))
             .requestEmail()
             .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void servicosFacebook(){

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AdicionarContaFacebookaoFirebase (loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

                Toast.makeText(getBaseContext(), "Cancelado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {

                String resultado = error.getMessage();

                TratamentoDeErros.opcoesErros(getBaseContext(),resultado);

            }
        });

    }



    //-----------------------------------------------AUTENTICAÇÃO NO FIREBASE------------------------------------

    private void AdicionarContaGoogleAoFirebase(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            startActivity(new Intent(getBaseContext(), TelaPrincipalActivity.class));
                            finish();

                        }else {

                            String resultado = task.getException().toString();
                            TratamentoDeErros.opcoesErros(getBaseContext(),resultado);

                        }
                    }
                });
    }

    private void AdicionarContaFacebookaoFirebase(AccessToken token){

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            startActivity(new Intent(getBaseContext(), TelaPrincipalActivity.class));

                            finish();
                        }

                    }
                });

    }


    private void AdicionarContaAnonimaAoFirebase(){

        auth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            startActivity(new Intent(getBaseContext(),TelaPrincipalActivity.class));
                            finish();

                        }else {

                            String resultado = task.getException().toString();
                            TratamentoDeErros.opcoesErros(getBaseContext(),resultado);

                        }

                    }
                });
    }

    //-----------------------------------------------METODOS DA ACTIVITY-------------------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode,resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 555){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

        try {

                    GoogleSignInAccount account = task.getResult(ApiException.class);

                    AdicionarContaGoogleAoFirebase(account);




        }catch (ApiException e){

            String resultado = e.getMessage();

            TratamentoDeErros.opcoesErros(getBaseContext(),resultado);
        }
    }
    }

    @Override
    protected void onStart(){
        super.onStart();

        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null) {

            auth.removeAuthStateListener(authStateListener);
        }

    }

}

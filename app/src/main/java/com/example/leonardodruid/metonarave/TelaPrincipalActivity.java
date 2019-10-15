package com.example.leonardodruid.metonarave;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

import com.example.leonardodruid.metonarave.database_lista_djs.DjsFragment;
import com.example.leonardodruid.metonarave.database_lista_parceiro.DatabaseListaParceiroFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.ListaRegioesBrasilFragment;
import com.example.leonardodruid.metonarave.sobre.SobreFragment;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class TelaPrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        //-------------------------Eventos de clicks-------------------------------

        ListaRegioesBrasilFragment listaRegioesBrasilFragment = new ListaRegioesBrasilFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameContainer, listaRegioesBrasilFragment);
        fragmentTransaction.commit();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {

            ListaRegioesBrasilFragment listaRegioesBrasilFragment = new ListaRegioesBrasilFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer,listaRegioesBrasilFragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_djs) {

            DjsFragment djsFragment = new DjsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer,djsFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_parceiros) {

            DatabaseListaParceiroFragment databaseListaParceiroFragment = new DatabaseListaParceiroFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer,databaseListaParceiroFragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_SobreNos) {

            SobreFragment sobreFragment = new SobreFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frameContainer, sobreFragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_Compartilhar_Aplicativo) {

            Intent compartilharIntent = new Intent(Intent.ACTION_SEND);
            compartilharIntent.setType("text/plain");
            compartilharIntent.putExtra(Intent.EXTRA_SUBJECT, "Compartilhar Aplicativo");
            String compartilharMensagem = "\n Quer ficar por dentro das Raves mais baladas das Regiões Sul e SulDeste? Clique no link abaixo\n\n";
            compartilharMensagem = compartilharMensagem + "https://play.google.com/store/apps?hl=pt_BR" + BuildConfig.APPLICATION_ID + "\n\n";
            compartilharIntent.putExtra(Intent.EXTRA_TEXT, compartilharMensagem);
            startActivity(Intent.createChooser(compartilharIntent, "Escolha um meio: " ));


        } else if (id == R.id.nav_Sair_Aplicativo) {

            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();


            //DeslogarGoogle

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    //.requestServerAuthCode(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            googleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
            googleSignInClient.signOut();

            Intent intent_VoltarTelaLogin = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent_VoltarTelaLogin);

            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

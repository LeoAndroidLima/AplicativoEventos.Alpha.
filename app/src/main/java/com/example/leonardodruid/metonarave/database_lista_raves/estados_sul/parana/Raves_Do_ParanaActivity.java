package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.parana;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.leonardodruid.metonarave.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Raves_Do_ParanaActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;

    private DatabaseReference reference_database;
    private boolean firebaseOffline = false;


    private Raves_Parana raves_parana;

    ImageView imageView_Foto_Da_Rave_Parana;
    ProgressBar progress_bar_Topo_Da_Rave_Parana;
    TextView textView_Rave_Nome_Parana, textView_Rave_Data_Parana, textView_Rave_Local_Parana,
            textView_Rave_Destalhes_Parana, textView_Rave_Comprar_Parana, textView_Dj_Rave_Parana_1,
            textView_Dj_Rave_Parana_2, textView_Dj_Rave_Parana_3, textView_Dj_Rave_Parana_4, textView_Dj_Rave_Parana_5,
            textView_Dj_Rave_Parana_6, textView_Dj_Rave_Parana_7, textView_Dj_Rave_Parana_8, textView_Dj_Rave_Parana_9,
            textView_Dj_Rave_Parana_10, textView_Dj_Rave_Parana_11, textView_Dj_Rave_Parana_12, textView_Aniversariante_Rave_Parana,
            textView_Rave_Acessar_Pagina_Parana;

    CardView cardView_Acessar_Instagram_Rave_Parana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__do__parana);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //-----------------ImageView-----------------
        imageView_Foto_Da_Rave_Parana = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Parana);

        //-----------------ProgressBar---------------

        progress_bar_Topo_Da_Rave_Parana = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Parana);

        //-----------------TextView------------------

        textView_Rave_Nome_Parana = (TextView)findViewById(R.id.textView_Rave_Nome_Parana);
        textView_Rave_Data_Parana = (TextView)findViewById(R.id.textView_Rave_Data_Parana);
        textView_Rave_Local_Parana = (TextView)findViewById(R.id.textView_Rave_Local_Parana);
        textView_Rave_Destalhes_Parana = (TextView)findViewById(R.id.textView_Rave_Destalhes_Parana);
        textView_Rave_Comprar_Parana = (TextView)findViewById(R.id.textView_Rave_Comprar_Parana);
        textView_Dj_Rave_Parana_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_1);
        textView_Dj_Rave_Parana_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_2);
        textView_Dj_Rave_Parana_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_3);
        textView_Dj_Rave_Parana_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_4);
        textView_Dj_Rave_Parana_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_5);
        textView_Dj_Rave_Parana_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_6);
        textView_Dj_Rave_Parana_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_7);
        textView_Dj_Rave_Parana_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_8);
        textView_Dj_Rave_Parana_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_9);
        textView_Dj_Rave_Parana_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_10);
        textView_Dj_Rave_Parana_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Parana_11);
        textView_Dj_Rave_Parana_12 =(TextView)findViewById(R.id.textView_Dj_Rave_Parana_12);
        textView_Aniversariante_Rave_Parana = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Parana);
        textView_Rave_Acessar_Pagina_Parana = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Parana);

        //-----------------CardView------------------

        cardView_Acessar_Instagram_Rave_Parana = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Parana);

        //---------------------Database------------------------

        database = FirebaseDatabase.getInstance();

        raves_parana = getIntent().getParcelableExtra("raves_parana");

        ativarFirebaseOffline();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
                default:
            return super.onOptionsItemSelected(item);
        }
    }

    //-----------------------------------------Ler Dados Offline--------------------------

    private void ativarFirebaseOffline(){

        try {
            if (!firebaseOffline){

                FirebaseDatabase.getInstance().setPersistenceEnabled(true);

                firebaseOffline = true;

            }else {


            }

        }catch (Exception e){


        }


    }

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Raves_Parana").child(raves_parana.getId()).child("Dados_Rave_Parana");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Parana = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Parana = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Parana = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Parana = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Parana = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Parana = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Parana_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Parana_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Parana_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Parana_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Parana_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Parana_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Parana_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Parana_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Parana_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Parana_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Parana_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Parana_12 = dataSnapshot.child("djRave_12").getValue(String.class);

                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Parana = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Parana = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String instagram_Rave_Parana = dataSnapshot.child("linkInstagram").getValue(String.class);


                    atualizarLayout(Imagem_Rave_Parana, Titulo_Rave_Parana, Data_Rave_Parana, Local_Rave_Parana, Detalhes_Rave_Parana, Aniversariante_Rave_Parana,
                            Dj_Rave_Parana_1, Dj_Rave_Parana_2, Dj_Rave_Parana_3, Dj_Rave_Parana_4, Dj_Rave_Parana_5, Dj_Rave_Parana_6, Dj_Rave_Parana_7,
                            Dj_Rave_Parana_8, Dj_Rave_Parana_9, Dj_Rave_Parana_10, Dj_Rave_Parana_11, Dj_Rave_Parana_12, Comprar_Rave_Parana, Pagina_Rave_Parana,
                            instagram_Rave_Parana);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }


    }

    private void atualizarLayout(String imagem_Rave_Parana, String titulo_Rave_Parana, String data_Rave_Parana,
                                 String local_Rave_Parana, String detalhes_Rave_Parana, String aniversariante_Rave_Parana,
                                 String dj_Rave_Parana_1, String dj_Rave_Parana_2, String dj_Rave_Parana_3, String dj_Rave_Parana_4,
                                 String dj_Rave_Parana_5, String dj_Rave_Parana_6, String dj_Rave_Parana_7, String dj_Rave_Parana_8,
                                 String dj_Rave_Parana_9, String dj_Rave_Parana_10, String dj_Rave_Parana_11, String dj_Rave_Parana_12,
                                 String comprar_Rave_Parana, String pagina_Rave_Parana, final String instagram_Rave_Parana){



        //------------------ProgresBar-----------------
        progress_bar_Topo_Da_Rave_Parana.setVisibility(View.VISIBLE);

        //------------------Textos---------------------
        textView_Rave_Nome_Parana.setText(titulo_Rave_Parana);
        textView_Rave_Data_Parana.setText(data_Rave_Parana);
        textView_Rave_Local_Parana.setText(local_Rave_Parana);
        textView_Rave_Destalhes_Parana.setText(detalhes_Rave_Parana);
        textView_Aniversariante_Rave_Parana.setText(aniversariante_Rave_Parana);

        textView_Rave_Acessar_Pagina_Parana.setText(pagina_Rave_Parana);
        textView_Rave_Comprar_Parana.setText(comprar_Rave_Parana);

        //----------------DJS----------------------------
        textView_Dj_Rave_Parana_1.setText(dj_Rave_Parana_1);
        textView_Dj_Rave_Parana_2.setText(dj_Rave_Parana_2);
        textView_Dj_Rave_Parana_3.setText(dj_Rave_Parana_3);
        textView_Dj_Rave_Parana_4.setText(dj_Rave_Parana_4);
        textView_Dj_Rave_Parana_5.setText(dj_Rave_Parana_5);
        textView_Dj_Rave_Parana_6.setText(dj_Rave_Parana_6);
        textView_Dj_Rave_Parana_7.setText(dj_Rave_Parana_7);
        textView_Dj_Rave_Parana_8.setText(dj_Rave_Parana_8);
        textView_Dj_Rave_Parana_9.setText(dj_Rave_Parana_9);
        textView_Dj_Rave_Parana_10.setText(dj_Rave_Parana_10);
        textView_Dj_Rave_Parana_11.setText(dj_Rave_Parana_11);
        textView_Dj_Rave_Parana_12.setText(dj_Rave_Parana_12);

        //--------------------------------Eventos de clicks---------------------

        cardView_Acessar_Instagram_Rave_Parana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(instagram_Rave_Parana);
                Intent iniciarBrowser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(iniciarBrowser);

            }
        });


        //--------------------------------Imagens---------------------

        Glide.with(getBaseContext()).load(imagem_Rave_Parana).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Parana.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Parana);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ouvinte();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reference_database != null){

            reference_database.removeEventListener(valueEventListener);

        }

    }
}

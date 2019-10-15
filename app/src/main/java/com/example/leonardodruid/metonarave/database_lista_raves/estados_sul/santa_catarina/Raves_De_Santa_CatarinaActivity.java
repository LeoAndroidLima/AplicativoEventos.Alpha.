package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.santa_catarina;

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

public class Raves_De_Santa_CatarinaActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private DatabaseReference reference_database;

    private Raves_Santa_Catarina raves_santa_catarina;

    private boolean firebaseOffline = false;

    //-----------------Layout------------------------------

    private ImageView imageView_Foto_Da_Rave_Santa_Catarina;

    private ProgressBar progress_bar_Topo_Da_Rave_Santa_Catarina;

    private TextView textView_Rave_Santa_Catarina, textView_Rave_Data_Santa_Catarina, textView_Rave_Local_Santa_Catarina, textView_Rave_Destalhes_Santa_Catarina,
            textView_Rave_Comprar_Santa_Catarina, textView_Dj_Rave_Santa_Catarina_1, textView_Dj_Rave_Santa_Catarina_2, textView_Dj_Rave_Santa_Catarina_3,
            textView_Dj_Rave_Santa_Catarina_4, textView_Dj_Rave_Santa_Catarina_5, textView_Dj_Rave_Santa_Catarina_6, textView_Dj_Rave_Santa_Catarina_7,
            textView_Dj_Rave_Santa_Catarina_8, textView_Dj_Rave_Santa_Catarina_9, textView_Dj_Rave_Santa_Catarina_10, textView_Dj_Rave_Santa_Catarina_11,
            textView_Dj_Rave_Santa_Catarina_12, textView_Aniversariante_Rave_Santa_Catarina, textView_Rave_Acessar_Pagina_Santa_Catarina;

    private CardView cardView_Acessar_Instagram_Rave_Santa_Catarina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__de__santa__catarina);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_Foto_Da_Rave_Santa_Catarina = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Santa_Catarina);

        progress_bar_Topo_Da_Rave_Santa_Catarina = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Santa_Catarina);

        textView_Rave_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Santa_Catarina);
        textView_Rave_Data_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Data_Santa_Catarina);
        textView_Rave_Local_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Local_Santa_Catarina);
        textView_Rave_Destalhes_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Destalhes_Santa_Catarina);
        textView_Rave_Comprar_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Comprar_Santa_Catarina);

        textView_Aniversariante_Rave_Santa_Catarina = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Santa_Catarina);
        textView_Rave_Acessar_Pagina_Santa_Catarina = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Santa_Catarina);

        textView_Dj_Rave_Santa_Catarina_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_1);
        textView_Dj_Rave_Santa_Catarina_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_2);
        textView_Dj_Rave_Santa_Catarina_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_3);
        textView_Dj_Rave_Santa_Catarina_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_4);
        textView_Dj_Rave_Santa_Catarina_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_5);
        textView_Dj_Rave_Santa_Catarina_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_6);
        textView_Dj_Rave_Santa_Catarina_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_7);
        textView_Dj_Rave_Santa_Catarina_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_8);
        textView_Dj_Rave_Santa_Catarina_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_9);
        textView_Dj_Rave_Santa_Catarina_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_10);
        textView_Dj_Rave_Santa_Catarina_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_11);
        textView_Dj_Rave_Santa_Catarina_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Santa_Catarina_12);

        cardView_Acessar_Instagram_Rave_Santa_Catarina = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Santa_Catarina);

        database = FirebaseDatabase.getInstance();

        raves_santa_catarina = getIntent().getParcelableExtra("raves_santa_catarina");

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

    private void ouvinte () {

        reference_database = database.getReference().child("BD").child("Raves_Santa_Catarina").child(raves_santa_catarina.getId()).child("Dados_Rave_Santa_Catarina");

        if (valueEventListener == null ){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Santa_Catarina = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Santa_Catarina = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Santa_Catarina = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Santa_Catarina = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Santa_Catarina = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Santa_Catarina = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Santa_Catarina_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Santa_Catarina_12 = dataSnapshot.child("djRave_12").getValue(String.class);

                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Santa_Catarina = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Santa_Catarina = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String instagram_Rave_Santa_Catarina = dataSnapshot.child("linkInstagram").getValue(String.class);

                    atualizarLayout(Imagem_Rave_Santa_Catarina, Titulo_Rave_Santa_Catarina, Data_Rave_Santa_Catarina, Local_Rave_Santa_Catarina,
                            Detalhes_Rave_Santa_Catarina, Aniversariante_Rave_Santa_Catarina, Dj_Rave_Santa_Catarina_1, Dj_Rave_Santa_Catarina_2,
                            Dj_Rave_Santa_Catarina_3, Dj_Rave_Santa_Catarina_4, Dj_Rave_Santa_Catarina_5, Dj_Rave_Santa_Catarina_6, Dj_Rave_Santa_Catarina_7,
                            Dj_Rave_Santa_Catarina_8, Dj_Rave_Santa_Catarina_9, Dj_Rave_Santa_Catarina_10, Dj_Rave_Santa_Catarina_11, Dj_Rave_Santa_Catarina_12,
                            Comprar_Rave_Santa_Catarina, Pagina_Rave_Santa_Catarina, instagram_Rave_Santa_Catarina);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }


    }

    private void atualizarLayout(String imagem_rave_santa_catarina, String titulo_rave_santa_catarina, String data_rave_santa_catarina, String local_rave_santa_catarina,
                                 String detalhes_rave_santa_catarina, String aniversariante_rave_santa_catarina, String dj_rave_santa_catarina_1, String dj_rave_santa_catarina_2,
                                 String dj_rave_santa_catarina_3, String dj_rave_santa_catarina_4, String dj_rave_santa_catarina_5, String dj_rave_santa_catarina_6, String dj_rave_santa_catarina_7,
                                 String dj_rave_santa_catarina_8, String dj_rave_santa_catarina_9, String dj_rave_santa_catarina_10, String dj_rave_santa_catarina_11, String dj_rave_santa_catarina_12,
                                 String comprar_rave_santa_catarina, String pagina_rave_santa_catarina, final String instagram_rave_santa_catarina) {

        //-------------------------------------Iniciando os Codigos----------------------------------------------------------------------------------------------------------------------

        //------------------ProgresBar-----------------
        progress_bar_Topo_Da_Rave_Santa_Catarina.setVisibility(View.VISIBLE);

        //------------------Textos---------------------
        textView_Rave_Santa_Catarina.setText(titulo_rave_santa_catarina);
        textView_Rave_Data_Santa_Catarina.setText(data_rave_santa_catarina);
        textView_Rave_Local_Santa_Catarina.setText(local_rave_santa_catarina);
        textView_Rave_Destalhes_Santa_Catarina.setText(detalhes_rave_santa_catarina);
        textView_Aniversariante_Rave_Santa_Catarina.setText(aniversariante_rave_santa_catarina);

        textView_Rave_Comprar_Santa_Catarina.setText(comprar_rave_santa_catarina);
        textView_Rave_Acessar_Pagina_Santa_Catarina.setText(pagina_rave_santa_catarina);

        //----------------DJS----------------------------

        textView_Dj_Rave_Santa_Catarina_1.setText(dj_rave_santa_catarina_1);
        textView_Dj_Rave_Santa_Catarina_2.setText(dj_rave_santa_catarina_2);
        textView_Dj_Rave_Santa_Catarina_3.setText(dj_rave_santa_catarina_3);
        textView_Dj_Rave_Santa_Catarina_4.setText(dj_rave_santa_catarina_4);
        textView_Dj_Rave_Santa_Catarina_5.setText(dj_rave_santa_catarina_5);
        textView_Dj_Rave_Santa_Catarina_6.setText(dj_rave_santa_catarina_6);
        textView_Dj_Rave_Santa_Catarina_7.setText(dj_rave_santa_catarina_7);
        textView_Dj_Rave_Santa_Catarina_8.setText(dj_rave_santa_catarina_8);
        textView_Dj_Rave_Santa_Catarina_9.setText(dj_rave_santa_catarina_9);
        textView_Dj_Rave_Santa_Catarina_10.setText(dj_rave_santa_catarina_10);
        textView_Dj_Rave_Santa_Catarina_11.setText(dj_rave_santa_catarina_11);
        textView_Dj_Rave_Santa_Catarina_12.setText(dj_rave_santa_catarina_12);


        //--------------------------------Eventos de clicks---------------------

        cardView_Acessar_Instagram_Rave_Santa_Catarina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(instagram_rave_santa_catarina);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        //--------------------------------Imagens---------------------

        Glide.with(getBaseContext()).load(imagem_rave_santa_catarina).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Santa_Catarina.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Santa_Catarina);

    }

    @Override
    protected void onStart() {
        super.onStart();

        ouvinte();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        reference_database.removeEventListener(valueEventListener);
    }
}

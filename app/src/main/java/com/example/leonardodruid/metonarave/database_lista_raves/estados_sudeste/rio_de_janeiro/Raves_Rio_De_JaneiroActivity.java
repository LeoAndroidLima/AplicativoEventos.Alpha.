package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.rio_de_janeiro;

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

public class Raves_Rio_De_JaneiroActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference_database;
    private ValueEventListener valueEventListener;

    private Raves_Rio_De_Janeiro raves_rio_de_janeiro;

    private boolean firebaseOffline = false;

    private ImageView imageView_Foto_Da_Rave_Rio_De_Janeiro;

    private ProgressBar progress_bar_Topo_Da_Rave_Rio_De_Janeiro;

    private TextView textView_Rave_Nome_Rio_De_Janeiro, textView_Rave_Data_Rio_De_Janeiro, textView_Rave_Local_Rio_De_Janeiro, textView_Rave_Destalhes_Rio_De_Janeiro,
            textView_Rave_Comprar_Rio_De_Janeiro, textView_Dj_Rave_Rio_De_Janeiro_1, textView_Dj_Rave_Rio_De_Janeiro_2, textView_Dj_Rave_Rio_De_Janeiro_3,
            textView_Dj_Rave_Rio_De_Janeiro_4, textView_Dj_Rave_Rio_De_Janeiro_5, textView_Dj_Rave_Rio_De_Janeiro_6, textView_Dj_Rave_Rio_De_Janeiro_7,
            textView_Dj_Rave_Rio_De_Janeiro_8, textView_Dj_Rave_Rio_De_Janeiro_9, textView_Dj_Rave_Rio_De_Janeiro_10, textView_Dj_Rave_Rio_De_Janeiro_11,
            textView_Dj_Rave_Rio_De_Janeiro_12, textView_Aniversariante_Rave_Rio_De_Janeiro, textView_Rave_Acessar_Pagina_Rio_De_Janeiro;

    private CardView cardView_Acessar_Instagram_Rave_Rio_De_Janeiro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__rio__de__janeiro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_Foto_Da_Rave_Rio_De_Janeiro = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Rio_De_Janeiro);

        progress_bar_Topo_Da_Rave_Rio_De_Janeiro = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Rio_De_Janeiro);

        textView_Rave_Nome_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Nome_Rio_De_Janeiro);
        textView_Rave_Data_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Data_Rio_De_Janeiro);
        textView_Rave_Local_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Local_Rio_De_Janeiro);
        textView_Rave_Destalhes_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Destalhes_Rio_De_Janeiro);
        textView_Rave_Comprar_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Comprar_Rio_De_Janeiro);
        textView_Aniversariante_Rave_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Rio_De_Janeiro);
        textView_Rave_Acessar_Pagina_Rio_De_Janeiro = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Rio_De_Janeiro);

        textView_Dj_Rave_Rio_De_Janeiro_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_1);
        textView_Dj_Rave_Rio_De_Janeiro_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_2);
        textView_Dj_Rave_Rio_De_Janeiro_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_3);
        textView_Dj_Rave_Rio_De_Janeiro_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_4);
        textView_Dj_Rave_Rio_De_Janeiro_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_5);
        textView_Dj_Rave_Rio_De_Janeiro_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_6);
        textView_Dj_Rave_Rio_De_Janeiro_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_7);
        textView_Dj_Rave_Rio_De_Janeiro_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_8);
        textView_Dj_Rave_Rio_De_Janeiro_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_9);
        textView_Dj_Rave_Rio_De_Janeiro_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_10);
        textView_Dj_Rave_Rio_De_Janeiro_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_11);
        textView_Dj_Rave_Rio_De_Janeiro_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_De_Janeiro_12);

        cardView_Acessar_Instagram_Rave_Rio_De_Janeiro = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Rio_De_Janeiro);

        ativarFirebaseOffline();
        database = FirebaseDatabase.getInstance();
        raves_rio_de_janeiro = getIntent().getParcelableExtra("raves_rio_de_janeiro");

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

        reference_database = database.getReference().child("BD").child("Raves_Rio_De_Janeiro").child(raves_rio_de_janeiro.getId()).child("Dados_Rave_Rio_De_Janeiro");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Rio_De_Janeiro = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Rio_De_Janeiro = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Rio_De_Janeiro = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Rio_De_Janeiro = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Rio_De_Janeiro = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Rio_De_Janeiro = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Rio_De_Janeiro_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Rio_De_Janeiro_12 = dataSnapshot.child("djRave_12").getValue(String.class);



                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Rio_De_Janeiro = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Rio_De_Janeiro = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String Instagram_Rave_Rio_De_Janeiro = dataSnapshot.child("linkInstagram").getValue(String.class);


                    atualizarLayout(Imagem_Rave_Rio_De_Janeiro, Titulo_Rave_Rio_De_Janeiro, Data_Rave_Rio_De_Janeiro, Local_Rave_Rio_De_Janeiro, Detalhes_Rave_Rio_De_Janeiro,
                            Aniversariante_Rave_Rio_De_Janeiro, Dj_Rave_Rio_De_Janeiro_1, Dj_Rave_Rio_De_Janeiro_2, Dj_Rave_Rio_De_Janeiro_3, Dj_Rave_Rio_De_Janeiro_4,
                            Dj_Rave_Rio_De_Janeiro_5, Dj_Rave_Rio_De_Janeiro_6, Dj_Rave_Rio_De_Janeiro_7, Dj_Rave_Rio_De_Janeiro_8, Dj_Rave_Rio_De_Janeiro_9,
                            Dj_Rave_Rio_De_Janeiro_10, Dj_Rave_Rio_De_Janeiro_11, Dj_Rave_Rio_De_Janeiro_12, Comprar_Rave_Rio_De_Janeiro, Pagina_Rave_Rio_De_Janeiro,
                            Instagram_Rave_Rio_De_Janeiro);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);

        }

    }


    public void atualizarLayout(String imagem_Rave_Rio_De_Janeiro, String titulo_Rave_Rio_De_Janeiro, String data_Rave_Rio_De_Janeiro, String local_Rave_Rio_De_Janeiro,
                                String detalhes_Rave_Rio_De_Janeiro, String aniversariante_Rave_Rio_De_Janeiro, String dj_Rave_Rio_De_Janeiro_1, String dj_Rave_Rio_De_Janeiro_2,
                                String dj_Rave_Rio_De_Janeiro_3, String dj_Rave_Rio_De_Janeiro_4, String dj_Rave_Rio_De_Janeiro_5, String dj_Rave_Rio_De_Janeiro_6,
                                String dj_Rave_Rio_De_Janeiro_7, String dj_Rave_Rio_De_Janeiro_8, String dj_Rave_Rio_De_Janeiro_9, String dj_Rave_Rio_De_Janeiro_10,
                                String dj_Rave_Rio_De_Janeiro_11, String dj_Rave_Rio_De_Janeiro_12, String comprar_Rave_Rio_De_Janeiro, String pagina_Rave_Rio_De_Janeiro, final String instagram_Rave_Rio_De_Janeiro){


        progress_bar_Topo_Da_Rave_Rio_De_Janeiro.setVisibility(View.VISIBLE);

        textView_Rave_Nome_Rio_De_Janeiro.setText(titulo_Rave_Rio_De_Janeiro);
        textView_Rave_Data_Rio_De_Janeiro.setText(data_Rave_Rio_De_Janeiro);
        textView_Rave_Local_Rio_De_Janeiro.setText(local_Rave_Rio_De_Janeiro);
        textView_Rave_Destalhes_Rio_De_Janeiro.setText(detalhes_Rave_Rio_De_Janeiro);
        textView_Aniversariante_Rave_Rio_De_Janeiro.setText(aniversariante_Rave_Rio_De_Janeiro);
        textView_Rave_Comprar_Rio_De_Janeiro.setText(comprar_Rave_Rio_De_Janeiro);
        textView_Rave_Acessar_Pagina_Rio_De_Janeiro.setText(pagina_Rave_Rio_De_Janeiro);

        textView_Dj_Rave_Rio_De_Janeiro_1.setText(dj_Rave_Rio_De_Janeiro_1);
        textView_Dj_Rave_Rio_De_Janeiro_2.setText(dj_Rave_Rio_De_Janeiro_2);
        textView_Dj_Rave_Rio_De_Janeiro_3.setText(dj_Rave_Rio_De_Janeiro_3);
        textView_Dj_Rave_Rio_De_Janeiro_4.setText(dj_Rave_Rio_De_Janeiro_4);
        textView_Dj_Rave_Rio_De_Janeiro_5.setText(dj_Rave_Rio_De_Janeiro_5);
        textView_Dj_Rave_Rio_De_Janeiro_6.setText(dj_Rave_Rio_De_Janeiro_6);
        textView_Dj_Rave_Rio_De_Janeiro_7.setText(dj_Rave_Rio_De_Janeiro_7);
        textView_Dj_Rave_Rio_De_Janeiro_8.setText(dj_Rave_Rio_De_Janeiro_8);
        textView_Dj_Rave_Rio_De_Janeiro_9.setText(dj_Rave_Rio_De_Janeiro_9);
        textView_Dj_Rave_Rio_De_Janeiro_10.setText(dj_Rave_Rio_De_Janeiro_10);
        textView_Dj_Rave_Rio_De_Janeiro_11.setText(dj_Rave_Rio_De_Janeiro_11);
        textView_Dj_Rave_Rio_De_Janeiro_12.setText(dj_Rave_Rio_De_Janeiro_12);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagem_Rave_Rio_De_Janeiro).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Rio_De_Janeiro.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Rio_De_Janeiro);

        //------------------------------------------Clicks-----------------------------------------------


        cardView_Acessar_Instagram_Rave_Rio_De_Janeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(instagram_Rave_Rio_De_Janeiro);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });

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

package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.minas_gerais;

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

public class Raves_Minas_GeraisActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private DatabaseReference reference_database;

    private boolean firebaseOffline = false;

    private Raves_Minas_Gerais raves_minas_gerais;

    private ImageView imageView_Foto_Da_Rave_Minas_Gerais;

    private ProgressBar progress_bar_Topo_Da_Rave_Minas_Gerais;

    private TextView textView_Rave_Nome_Minas_Gerais, textView_Rave_Data_Minas_Gerais, textView_Rave_Local_Minas_Gerais, textView_Rave_Destalhes_Minas_Gerais,
            textView_Rave_Comprar_Minas_Gerais, textView_Dj_Rave_Minas_Gerais_1, textView_Dj_Rave_Minas_Gerais_2, textView_Dj_Rave_Minas_Gerais_3,
            textView_Dj_Rave_Minas_Gerais_4, textView_Dj_Rave_Minas_Gerais_5, textView_Dj_Rave_Minas_Gerais_6, textView_Dj_Rave_Minas_Gerais_7,
            textView_Dj_Rave_Minas_Gerais_8, textView_Dj_Rave_Minas_Gerais_9, textView_Dj_Rave_Minas_Gerais_10, textView_Dj_Rave_Minas_Gerais_11,
            textView_Dj_Rave_Minas_Gerais_12, textView_Aniversariante_Rave_Minas_Gerais, textView_Rave_Acessar_Pagina_Minas_Gerais;

    private CardView cardView_Acessar_Instagram_Rave_Minas_Gerais;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__minas__gerais);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_Foto_Da_Rave_Minas_Gerais = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Minas_Gerais);

        progress_bar_Topo_Da_Rave_Minas_Gerais = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Minas_Gerais);

        textView_Rave_Nome_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Nome_Minas_Gerais);
        textView_Rave_Data_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Data_Minas_Gerais);
        textView_Rave_Local_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Local_Minas_Gerais);
        textView_Rave_Destalhes_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Destalhes_Minas_Gerais);
        textView_Rave_Comprar_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Comprar_Minas_Gerais);

        textView_Dj_Rave_Minas_Gerais_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_1);
        textView_Dj_Rave_Minas_Gerais_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_2);
        textView_Dj_Rave_Minas_Gerais_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_3);
        textView_Dj_Rave_Minas_Gerais_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_4);
        textView_Dj_Rave_Minas_Gerais_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_5);
        textView_Dj_Rave_Minas_Gerais_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_6);
        textView_Dj_Rave_Minas_Gerais_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_7);
        textView_Dj_Rave_Minas_Gerais_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_8);
        textView_Dj_Rave_Minas_Gerais_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_9);
        textView_Dj_Rave_Minas_Gerais_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_10);
        textView_Dj_Rave_Minas_Gerais_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_11);
        textView_Dj_Rave_Minas_Gerais_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Minas_Gerais_12);

        textView_Aniversariante_Rave_Minas_Gerais = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Minas_Gerais);
        textView_Rave_Acessar_Pagina_Minas_Gerais = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Minas_Gerais);

        cardView_Acessar_Instagram_Rave_Minas_Gerais = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Minas_Gerais);

        ativarFirebaseOffline();
        database = FirebaseDatabase.getInstance();

        raves_minas_gerais = getIntent().getParcelableExtra("raves_minas_gerais");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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


        reference_database = database.getReference().child("BD").child("Raves_Minas_Gerais").child(raves_minas_gerais.getId()).child("Dados_Rave_Minas_Gerais");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Minas_Gerais = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Minas_Gerais = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Minas_Gerais = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Minas_Gerais = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Minas_Gerais = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Minas_Gerais = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Minas_Gerais_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Minas_Gerais_12 = dataSnapshot.child("djRave_12").getValue(String.class);


                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Minas_Gerais = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Minas_Gerais = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String Instagram_Rave_Minas_Gerais = dataSnapshot.child("linkInstagram").getValue(String.class);

                    atualizarLayout(Imagem_Rave_Minas_Gerais, Titulo_Rave_Minas_Gerais, Data_Rave_Minas_Gerais,
                            Local_Rave_Minas_Gerais, Detalhes_Rave_Minas_Gerais, Aniversariante_Rave_Minas_Gerais, Dj_Rave_Minas_Gerais_1,
                            Dj_Rave_Minas_Gerais_2, Dj_Rave_Minas_Gerais_3, Dj_Rave_Minas_Gerais_4, Dj_Rave_Minas_Gerais_5,
                            Dj_Rave_Minas_Gerais_6, Dj_Rave_Minas_Gerais_7, Dj_Rave_Minas_Gerais_8, Dj_Rave_Minas_Gerais_9,
                            Dj_Rave_Minas_Gerais_10, Dj_Rave_Minas_Gerais_11, Dj_Rave_Minas_Gerais_12, Comprar_Rave_Minas_Gerais,
                            Pagina_Rave_Minas_Gerais, Instagram_Rave_Minas_Gerais);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }

    }

    private void atualizarLayout(String imagem_rave_minas_gerais, String titulo_rave_minas_gerais, String data_rave_minas_gerais,
                                 String local_rave_minas_gerais, String detalhes_rave_minas_gerais, String aniversariante_rave_minas_gerais, String dj_rave_minas_gerais_1,
                                 String dj_rave_minas_gerais_2, String dj_rave_minas_gerais_3, String dj_rave_minas_gerais_4, String dj_rave_minas_gerais_5, String dj_rave_minas_gerais_6,
                                 String dj_rave_minas_gerais_7, String dj_rave_minas_gerais_8, String dj_rave_minas_gerais_9, String dj_rave_minas_gerais_10, String dj_rave_minas_gerais_11,
                                 String dj_rave_minas_gerais_12, String comprar_rave_minas_gerais, String pagina_rave_minas_gerais, final String instagram_rave_minas_gerais) {


        progress_bar_Topo_Da_Rave_Minas_Gerais.setVisibility(View.VISIBLE);

        textView_Rave_Nome_Minas_Gerais.setText(titulo_rave_minas_gerais);
        textView_Rave_Data_Minas_Gerais.setText(data_rave_minas_gerais);
        textView_Rave_Local_Minas_Gerais.setText(local_rave_minas_gerais);
        textView_Rave_Destalhes_Minas_Gerais.setText(detalhes_rave_minas_gerais);
        textView_Aniversariante_Rave_Minas_Gerais.setText(aniversariante_rave_minas_gerais);
        textView_Rave_Comprar_Minas_Gerais.setText(comprar_rave_minas_gerais);
        textView_Rave_Acessar_Pagina_Minas_Gerais.setText(pagina_rave_minas_gerais);

        textView_Dj_Rave_Minas_Gerais_1.setText(dj_rave_minas_gerais_1);
        textView_Dj_Rave_Minas_Gerais_2.setText(dj_rave_minas_gerais_2);
        textView_Dj_Rave_Minas_Gerais_3.setText(dj_rave_minas_gerais_3);
        textView_Dj_Rave_Minas_Gerais_4.setText(dj_rave_minas_gerais_4);
        textView_Dj_Rave_Minas_Gerais_5.setText(dj_rave_minas_gerais_5);
        textView_Dj_Rave_Minas_Gerais_6.setText(dj_rave_minas_gerais_6);
        textView_Dj_Rave_Minas_Gerais_7.setText(dj_rave_minas_gerais_7);
        textView_Dj_Rave_Minas_Gerais_8.setText(dj_rave_minas_gerais_8);
        textView_Dj_Rave_Minas_Gerais_9.setText(dj_rave_minas_gerais_9);
        textView_Dj_Rave_Minas_Gerais_10.setText(dj_rave_minas_gerais_10);
        textView_Dj_Rave_Minas_Gerais_11.setText(dj_rave_minas_gerais_11);
        textView_Dj_Rave_Minas_Gerais_12.setText(dj_rave_minas_gerais_12);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagem_rave_minas_gerais).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Minas_Gerais.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Minas_Gerais);

        //------------------------------------------Clicks-----------------------------------------------

        cardView_Acessar_Instagram_Rave_Minas_Gerais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(instagram_rave_minas_gerais);
                Intent lauch = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(lauch);

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

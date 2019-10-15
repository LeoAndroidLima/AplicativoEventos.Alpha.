package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.espirito_santo;

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

public class Raves_Espirito_SantoActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private DatabaseReference reference_database;
    private Rave_Espirito_Santo rave_espirito_santo;

    private boolean firebaseOffline = false;


    private ImageView imageView_Foto_Da_Rave_Espirito_Santo;

    private ProgressBar progress_bar_Topo_Da_Rave_Espirito_Santo;

    private TextView textView_Rave_Nome_Espirito_Santo, textView_Rave_Data_Espirito_Santo, textView_Rave_Local_Espirito_Santo, textView_Rave_Destalhes_Espirito_Santo,
            textView_Rave_Comprar_Espirito_Santo, textView_Dj_Rave_Espirito_Santo_1, textView_Dj_Rave_Espirito_Santo_2, textView_Dj_Rave_Espirito_Santo_3, textView_Dj_Rave_Espirito_Santo_4,
            textView_Dj_Rave_Espirito_Santo_5, textView_Dj_Rave_Espirito_Santo_6, textView_Dj_Rave_Espirito_Santo_7, textView_Dj_Rave_Espirito_Santo_8,
            textView_Dj_Rave_Espirito_Santo_9, textView_Dj_Rave_Espirito_Santo_10, textView_Dj_Rave_Espirito_Santo_11, textView_Dj_Rave_Espirito_Santo_12,
            textView_Aniversariante_Rave_Espirito_Santo, textView_Rave_Acessar_Pagina_Espirito_Santo;

    private CardView cardView_Acessar_Instagram_Rave_Espirito_Santo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__espirito__santo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView_Foto_Da_Rave_Espirito_Santo = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Espirito_Santo);

        progress_bar_Topo_Da_Rave_Espirito_Santo = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Espirito_Santo);

        textView_Rave_Nome_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Nome_Espirito_Santo);
        textView_Rave_Data_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Data_Espirito_Santo);
        textView_Rave_Local_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Local_Espirito_Santo);
        textView_Rave_Destalhes_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Destalhes_Espirito_Santo);
        textView_Rave_Comprar_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Comprar_Espirito_Santo);

        textView_Aniversariante_Rave_Espirito_Santo = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Espirito_Santo);
        textView_Rave_Acessar_Pagina_Espirito_Santo = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Espirito_Santo);

        textView_Dj_Rave_Espirito_Santo_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_1);
        textView_Dj_Rave_Espirito_Santo_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_2);
        textView_Dj_Rave_Espirito_Santo_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_3);
        textView_Dj_Rave_Espirito_Santo_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_4);
        textView_Dj_Rave_Espirito_Santo_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_5);
        textView_Dj_Rave_Espirito_Santo_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_6);
        textView_Dj_Rave_Espirito_Santo_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_7);
        textView_Dj_Rave_Espirito_Santo_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_8);
        textView_Dj_Rave_Espirito_Santo_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_9);
        textView_Dj_Rave_Espirito_Santo_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_10);
        textView_Dj_Rave_Espirito_Santo_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_11);
        textView_Dj_Rave_Espirito_Santo_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Espirito_Santo_12);

        cardView_Acessar_Instagram_Rave_Espirito_Santo = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Espirito_Santo);

        ativarFirebaseOffline();
        database = FirebaseDatabase.getInstance();

        rave_espirito_santo = getIntent().getParcelableExtra("raves_espirito_santo");

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


        reference_database = database.getReference().child("BD").child("Raves_Espirito_Santo").child(rave_espirito_santo.getId()).child("Dados_Rave_Espirito_Santo");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Espirito_Santo = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Espirito_Santo = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Espirito_Santo = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Espirito_Santo = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Espirito_Santo = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Espirito_Santo = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Espirito_Santo_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Espirito_Santo_12 = dataSnapshot.child("djRave_12").getValue(String.class);




                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Espirito_Santo = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Espirito_Santo = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String Instagram_Rave_Espirito_Santo = dataSnapshot.child("linkInstagram").getValue(String.class);

                    atualizarLayout(Imagem_Rave_Espirito_Santo, Titulo_Rave_Espirito_Santo, Data_Rave_Espirito_Santo, Local_Rave_Espirito_Santo,
                            Detalhes_Rave_Espirito_Santo, Aniversariante_Espirito_Santo, Dj_Rave_Espirito_Santo_1, Dj_Rave_Espirito_Santo_2,
                            Dj_Rave_Espirito_Santo_3, Dj_Rave_Espirito_Santo_4, Dj_Rave_Espirito_Santo_5, Dj_Rave_Espirito_Santo_6,
                            Dj_Rave_Espirito_Santo_7, Dj_Rave_Espirito_Santo_8, Dj_Rave_Espirito_Santo_9, Dj_Rave_Espirito_Santo_10,
                            Dj_Rave_Espirito_Santo_11, Dj_Rave_Espirito_Santo_12, Comprar_Rave_Espirito_Santo, Pagina_Rave_Espirito_Santo, Instagram_Rave_Espirito_Santo);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }

    }

    private void atualizarLayout(String imagem_rave_espirito_santo, String titulo_rave_espirito_santo, String data_rave_espirito_santo, String local_rave_espirito_santo,
                                 String detalhes_rave_espirito_santo, String aniversariante_espirito_santo, String dj_rave_espirito_santo_1, String dj_rave_espirito_santo_2,
                                 String dj_rave_espirito_santo_3, String dj_rave_espirito_santo_4, String dj_rave_espirito_santo_5, String dj_rave_espirito_santo_6, String dj_rave_espirito_santo_7,
                                 String dj_rave_espirito_santo_8, String dj_rave_espirito_santo_9, String dj_rave_espirito_santo_10, String dj_rave_espirito_santo_11, String dj_rave_espirito_santo_12,
                                 String comprar_rave_espirito_santo, String pagina_rave_espirito_santo, final String instagram_rave_espirito_santo) {


        progress_bar_Topo_Da_Rave_Espirito_Santo.setVisibility(View.VISIBLE);

        textView_Rave_Nome_Espirito_Santo.setText(titulo_rave_espirito_santo);
        textView_Rave_Data_Espirito_Santo.setText(data_rave_espirito_santo);
        textView_Rave_Local_Espirito_Santo.setText(local_rave_espirito_santo);
        textView_Rave_Destalhes_Espirito_Santo.setText(detalhes_rave_espirito_santo);
        textView_Aniversariante_Rave_Espirito_Santo.setText(aniversariante_espirito_santo);

        textView_Dj_Rave_Espirito_Santo_1.setText(dj_rave_espirito_santo_1);
        textView_Dj_Rave_Espirito_Santo_2.setText(dj_rave_espirito_santo_2);
        textView_Dj_Rave_Espirito_Santo_3.setText(dj_rave_espirito_santo_3);
        textView_Dj_Rave_Espirito_Santo_4.setText(dj_rave_espirito_santo_4);
        textView_Dj_Rave_Espirito_Santo_5.setText(dj_rave_espirito_santo_5);
        textView_Dj_Rave_Espirito_Santo_6.setText(dj_rave_espirito_santo_6);
        textView_Dj_Rave_Espirito_Santo_7.setText(dj_rave_espirito_santo_7);
        textView_Dj_Rave_Espirito_Santo_8.setText(dj_rave_espirito_santo_8);
        textView_Dj_Rave_Espirito_Santo_9.setText(dj_rave_espirito_santo_9);
        textView_Dj_Rave_Espirito_Santo_10.setText(dj_rave_espirito_santo_10);
        textView_Dj_Rave_Espirito_Santo_11.setText(dj_rave_espirito_santo_11);
        textView_Dj_Rave_Espirito_Santo_12.setText(dj_rave_espirito_santo_12);

        textView_Rave_Comprar_Espirito_Santo.setText(comprar_rave_espirito_santo);
        textView_Rave_Acessar_Pagina_Espirito_Santo.setText(pagina_rave_espirito_santo);



        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagem_rave_espirito_santo).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Espirito_Santo.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Espirito_Santo);

        //------------------------------------------Clicks-----------------------------------------------

        cardView_Acessar_Instagram_Rave_Espirito_Santo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uriUrl = Uri.parse(instagram_rave_espirito_santo);
                Intent lauchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(lauchBrowser);

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

package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.sao_paulo;

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

public class Raves_De_Sao_PauloActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;
    private DatabaseReference reference_database;

    private Raves_Sao_Paulo raves_sao_paulo;

    private boolean firebaseOffline = false;

    private ImageView imageView_Foto_Da_Rave_Sao_Paulo;

    private ProgressBar progress_bar_Topo_Da_Rave_Sao_Paulo;

    private TextView textView_Rave_Nome_Sao_Paulo, textView_Rave_Data_Sao_Paulo, textView_Rave_Local_Sao_Paulo, textView_Rave_Destalhes_Sao_Paulo,
            textView_Rave_Comprar_Sao_Paulo, textView_Dj_Rave_Sao_Paulo_1, textView_Dj_Rave_Sao_Paulo_2, textView_Dj_Rave_Sao_Paulo_3,
            textView_Dj_Rave_Sao_Paulo_4, textView_Dj_Rave_Sao_Paulo_5, textView_Dj_Rave_Sao_Paulo_6, textView_Dj_Rave_Sao_Paulo_7,
            textView_Dj_Rave_Sao_Paulo_8, textView_Dj_Rave_Sao_Paulo_9, textView_Dj_Rave_Sao_Paulo_10, textView_Dj_Rave_Sao_Paulo_11,
            textView_Dj_Rave_Sao_Paulo_12, textView_Aniversariante_Rave_Sao_Paulo, textView_Rave_Acessar_Pagina_Sao_Paulo;

    private CardView cardView_Acessar_Instagram_Rave_Sao_Paulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__de__sao__paulo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_Foto_Da_Rave_Sao_Paulo = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Sao_Paulo);

        progress_bar_Topo_Da_Rave_Sao_Paulo = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Sao_Paulo);

        textView_Rave_Nome_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Nome_Sao_Paulo);
        textView_Rave_Data_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Data_Sao_Paulo);
        textView_Rave_Local_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Local_Sao_Paulo);
        textView_Rave_Destalhes_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Destalhes_Sao_Paulo);
        textView_Rave_Comprar_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Comprar_Sao_Paulo);
        textView_Aniversariante_Rave_Sao_Paulo = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Sao_Paulo);
        textView_Rave_Acessar_Pagina_Sao_Paulo = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Sao_Paulo);

        textView_Dj_Rave_Sao_Paulo_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_1);
        textView_Dj_Rave_Sao_Paulo_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_2);
        textView_Dj_Rave_Sao_Paulo_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_3);
        textView_Dj_Rave_Sao_Paulo_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_4);
        textView_Dj_Rave_Sao_Paulo_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_5);
        textView_Dj_Rave_Sao_Paulo_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_6);
        textView_Dj_Rave_Sao_Paulo_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_7);
        textView_Dj_Rave_Sao_Paulo_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_8);
        textView_Dj_Rave_Sao_Paulo_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_9);
        textView_Dj_Rave_Sao_Paulo_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_10);
        textView_Dj_Rave_Sao_Paulo_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_11);
        textView_Dj_Rave_Sao_Paulo_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Sao_Paulo_12);

        cardView_Acessar_Instagram_Rave_Sao_Paulo = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Sao_Paulo);

        ativarFirebaseOffline();
        database = FirebaseDatabase.getInstance();
        raves_sao_paulo = getIntent().getParcelableExtra("raves_sao_paulo");
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


        reference_database = database.getReference().child("BD").child("Raves_Sao_Paulo").child(raves_sao_paulo.getId()).child("Dados_Rave_Sao_Paulo");

        if (valueEventListener == null){


            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Sao_Paulo = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Sao_Paulo = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Sao_Paulo = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Sao_Paulo = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Sao_Paulo = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Sao_Paulo = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Sao_Paulo_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Sao_Paulo_12 = dataSnapshot.child("djRave_12").getValue(String.class);


                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Sao_Paulo = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Sao_Paulo = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String Instagram_Rave_Sao_Paulo = dataSnapshot.child("linkInstagram").getValue(String.class);


                    atualizarLayout(Imagem_Rave_Sao_Paulo, Titulo_Rave_Sao_Paulo, Data_Rave_Sao_Paulo, Local_Rave_Sao_Paulo, Detalhes_Rave_Sao_Paulo,
                            Aniversariante_Rave_Sao_Paulo, Dj_Rave_Sao_Paulo_1, Dj_Rave_Sao_Paulo_2, Dj_Rave_Sao_Paulo_3, Dj_Rave_Sao_Paulo_4,
                            Dj_Rave_Sao_Paulo_5, Dj_Rave_Sao_Paulo_6, Dj_Rave_Sao_Paulo_7, Dj_Rave_Sao_Paulo_8, Dj_Rave_Sao_Paulo_9,
                            Dj_Rave_Sao_Paulo_10, Dj_Rave_Sao_Paulo_11, Dj_Rave_Sao_Paulo_12, Comprar_Rave_Sao_Paulo, Pagina_Rave_Sao_Paulo, Instagram_Rave_Sao_Paulo);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);

        }

    }

    private void atualizarLayout(String imagem_rave_sao_paulo, String titulo_rave_sao_paulo, String data_rave_sao_paulo, String local_rave_sao_paulo, String detalhes_rave_sao_paulo,
                                 String aniversariante_rave_sao_paulo, String dj_rave_sao_paulo_1, String dj_rave_sao_paulo_2, String dj_rave_sao_paulo_3, String dj_rave_sao_paulo_4,
                                 String dj_rave_sao_paulo_5, String dj_rave_sao_paulo_6, String dj_rave_sao_paulo_7, String dj_rave_sao_paulo_8, String dj_rave_sao_paulo_9,
                                 String dj_rave_sao_paulo_10, String dj_rave_sao_paulo_11, String dj_rave_sao_paulo_12, String Comprar_Rave_Sao_Paulo, String Pagina_Rave_Sao_Paulo,
                                 final String Instagram_Rave_Sao_Paulo) {

        progress_bar_Topo_Da_Rave_Sao_Paulo.setVisibility(View.VISIBLE);

        textView_Rave_Nome_Sao_Paulo.setText(titulo_rave_sao_paulo);
        textView_Rave_Data_Sao_Paulo.setText(data_rave_sao_paulo);
        textView_Rave_Local_Sao_Paulo.setText(local_rave_sao_paulo);
        textView_Rave_Destalhes_Sao_Paulo.setText(detalhes_rave_sao_paulo);
        textView_Aniversariante_Rave_Sao_Paulo.setText(aniversariante_rave_sao_paulo);
        textView_Rave_Comprar_Sao_Paulo.setText(Comprar_Rave_Sao_Paulo);
        textView_Rave_Acessar_Pagina_Sao_Paulo.setText(Pagina_Rave_Sao_Paulo);

        textView_Dj_Rave_Sao_Paulo_1.setText(dj_rave_sao_paulo_1);
        textView_Dj_Rave_Sao_Paulo_2.setText(dj_rave_sao_paulo_2);
        textView_Dj_Rave_Sao_Paulo_3.setText(dj_rave_sao_paulo_3);
        textView_Dj_Rave_Sao_Paulo_4.setText(dj_rave_sao_paulo_4);
        textView_Dj_Rave_Sao_Paulo_5.setText(dj_rave_sao_paulo_5);
        textView_Dj_Rave_Sao_Paulo_6.setText(dj_rave_sao_paulo_6);
        textView_Dj_Rave_Sao_Paulo_7.setText(dj_rave_sao_paulo_7);
        textView_Dj_Rave_Sao_Paulo_8.setText(dj_rave_sao_paulo_8);
        textView_Dj_Rave_Sao_Paulo_9.setText(dj_rave_sao_paulo_9);
        textView_Dj_Rave_Sao_Paulo_10.setText(dj_rave_sao_paulo_10);
        textView_Dj_Rave_Sao_Paulo_11.setText(dj_rave_sao_paulo_11);
        textView_Dj_Rave_Sao_Paulo_12.setText(dj_rave_sao_paulo_12);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagem_rave_sao_paulo).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Sao_Paulo.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Sao_Paulo);

        //------------------------------------------Clicks-----------------------------------------------

        cardView_Acessar_Instagram_Rave_Sao_Paulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(Instagram_Rave_Sao_Paulo);
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



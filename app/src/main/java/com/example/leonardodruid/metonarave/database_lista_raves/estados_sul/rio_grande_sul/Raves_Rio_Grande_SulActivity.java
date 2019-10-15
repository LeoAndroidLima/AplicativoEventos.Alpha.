package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.rio_grande_sul;

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

public class Raves_Rio_Grande_SulActivity extends AppCompatActivity {

    private ImageView imageView_Foto_Da_Rave_Rio_Grande_Sul;
    private ProgressBar progress_bar_Topo_Da_Rave_Rio_Grande_Sul;

    private TextView textView_Rave_Rio_Grande_Sul, textView_Rave_Data_Rio_Grande_Sul, textView_Rave_Local_Rio_Grande_Sul, textView_Rave_Destalhes_Rio_Grande_Sul,
            textView_Rave_Comprar_Rio_Grande_Sul, textView_Dj_Rave_Rio_Grande_Sul_1, textView_Dj_Rave_Rio_Grande_Sul_2, textView_Dj_Rave_Rio_Grande_Sul_3,
            textView_Dj_Rave_Rio_Grande_Sul_4, textView_Dj_Rave_Rio_Grande_Sul_5, textView_Dj_Rave_Rio_Grande_Sul_6, textView_Dj_Rave_Rio_Grande_Sul_7,
            textView_Dj_Rave_Rio_Grande_Sul_8, textView_Dj_Rave_Rio_Grande_Sul_9, textView_Dj_Rave_Rio_Grande_Sul_10, textView_Dj_Rave_Rio_Grande_Sul_11,
            textView_Dj_Rave_Rio_Grande_Sul_12, textView_Aniversariante_Rave_Rio_Grande_Sul, textView_Rave_Acessar_Pagina_Rio_Grande_Sul;

    private CardView cardView_Acessar_Instagram_Rave_Rio_Grande_Sul;

    private FirebaseDatabase database;
    private DatabaseReference reference_database;
    private ValueEventListener valueEventListener;

    private Raves_Rio_Grande_Sul raves_rio_grande_sul;

    private boolean firebaseOffline = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raves__rio__grande__sul);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_Foto_Da_Rave_Rio_Grande_Sul = (ImageView)findViewById(R.id.imageView_Foto_Da_Rave_Rio_Grande_Sul);

        progress_bar_Topo_Da_Rave_Rio_Grande_Sul = (ProgressBar)findViewById(R.id.progress_bar_Topo_Da_Rave_Rio_Grande_Sul);

        textView_Rave_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Rio_Grande_Sul);
        textView_Rave_Data_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Data_Rio_Grande_Sul);
        textView_Rave_Local_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Local_Rio_Grande_Sul);
        textView_Rave_Destalhes_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Destalhes_Rio_Grande_Sul);
        textView_Rave_Comprar_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Comprar_Rio_Grande_Sul);

        textView_Dj_Rave_Rio_Grande_Sul_1 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_1);
        textView_Dj_Rave_Rio_Grande_Sul_2 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_2);
        textView_Dj_Rave_Rio_Grande_Sul_3 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_3);
        textView_Dj_Rave_Rio_Grande_Sul_4 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_4);
        textView_Dj_Rave_Rio_Grande_Sul_5 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_5);
        textView_Dj_Rave_Rio_Grande_Sul_6 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_6);
        textView_Dj_Rave_Rio_Grande_Sul_7 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_7);
        textView_Dj_Rave_Rio_Grande_Sul_8 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_8);
        textView_Dj_Rave_Rio_Grande_Sul_9 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_9);
        textView_Dj_Rave_Rio_Grande_Sul_10 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_10);
        textView_Dj_Rave_Rio_Grande_Sul_11 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_11);
        textView_Dj_Rave_Rio_Grande_Sul_12 = (TextView)findViewById(R.id.textView_Dj_Rave_Rio_Grande_Sul_12);

        textView_Aniversariante_Rave_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Aniversariante_Rave_Rio_Grande_Sul);
        textView_Rave_Acessar_Pagina_Rio_Grande_Sul = (TextView)findViewById(R.id.textView_Rave_Acessar_Pagina_Rio_Grande_Sul);

        cardView_Acessar_Instagram_Rave_Rio_Grande_Sul = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Rave_Rio_Grande_Sul);

        database = FirebaseDatabase.getInstance();

        raves_rio_grande_sul = getIntent().getParcelableExtra("raves_rio_grande_sul");

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



        reference_database = database.getReference().child("BD").child("Raves_Rio_Grande_Sul").child(raves_rio_grande_sul.getId()).child("Dados_Rave_Rio_Grande_Sul");

        if (valueEventListener == null){


            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------------IMAGEMS-----------------
                    String Imagem_Rave_Rio_Grande_Sul = dataSnapshot.child("imageRave").getValue(String.class);

                    //-----------------TEXTOS---------------------
                    String Titulo_Rave_Rio_Grande_Sul = dataSnapshot.child("nomeRave").getValue(String.class);
                    String Data_Rave_Rio_Grande_Sul = dataSnapshot.child("dataRave").getValue(String.class);
                    String Local_Rave_Rio_Grande_Sul = dataSnapshot.child("localRave").getValue(String.class);
                    String Detalhes_Rave_Rio_Grande_Sul = dataSnapshot.child("detalhesRave").getValue(String.class);
                    String Aniversariante_Rave_Rio_Grande_Sula = dataSnapshot.child("aniversarioRave").getValue(String.class);

                    //-----------------DJS---------------------
                    String Dj_Rave_Rio_Grande_Sul_1 = dataSnapshot.child("djRave_1").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_2 = dataSnapshot.child("djRave_2").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_3 = dataSnapshot.child("djRave_3").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_4 = dataSnapshot.child("djRave_4").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_5 = dataSnapshot.child("djRave_5").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_6 = dataSnapshot.child("djRave_6").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_7 = dataSnapshot.child("djRave_7").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_8 = dataSnapshot.child("djRave_8").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_9 = dataSnapshot.child("djRave_9").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_10 = dataSnapshot.child("djRave_10").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_11 = dataSnapshot.child("djRave_11").getValue(String.class);
                    String Dj_Rave_Rio_Grande_Sul_12 = dataSnapshot.child("djRave_12").getValue(String.class);


                    //-----------------CLICKS---------------------
                    String Comprar_Rave_Rio_Grande_Sul = dataSnapshot.child("linkComprar").getValue(String.class);
                    String Pagina_Rave_Rio_Grande_Sul = dataSnapshot.child("linkPaginaFacebook").getValue(String.class);
                    String instagram_Rave_Rio_Grande_Sul = dataSnapshot.child("linkInstagram").getValue(String.class);


                    atualizarLayout(Imagem_Rave_Rio_Grande_Sul, Titulo_Rave_Rio_Grande_Sul, Data_Rave_Rio_Grande_Sul, Local_Rave_Rio_Grande_Sul,
                            Detalhes_Rave_Rio_Grande_Sul, Aniversariante_Rave_Rio_Grande_Sula, Dj_Rave_Rio_Grande_Sul_1, Dj_Rave_Rio_Grande_Sul_2,
                            Dj_Rave_Rio_Grande_Sul_3, Dj_Rave_Rio_Grande_Sul_4, Dj_Rave_Rio_Grande_Sul_5, Dj_Rave_Rio_Grande_Sul_6,
                            Dj_Rave_Rio_Grande_Sul_7, Dj_Rave_Rio_Grande_Sul_8, Dj_Rave_Rio_Grande_Sul_9, Dj_Rave_Rio_Grande_Sul_10,
                            Dj_Rave_Rio_Grande_Sul_11, Dj_Rave_Rio_Grande_Sul_12, Comprar_Rave_Rio_Grande_Sul, Pagina_Rave_Rio_Grande_Sul,
                            instagram_Rave_Rio_Grande_Sul);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }


    }

    private void atualizarLayout(String imagem_Rave_Rio_Grande_Sul, String titulo_Rave_Rio_Grande_Sul, String data_Rave_Rio_Grande_Sul, String local_Rave_Rio_Grande_Sul,
                                 String detalhes_Rave_Rio_Grande_Sul, String aniversariante_Rave_Rio_Grande_Sula, String dj_Rave_Rio_Grande_Sul_1, String dj_Rave_Rio_Grande_Sul_2,
                                 String dj_Rave_Rio_Grande_Sul_3, String dj_Rave_Rio_Grande_Sul_4, String dj_Rave_Rio_Grande_Sul_5, String dj_Rave_Rio_Grande_Sul_6,
                                 String dj_Rave_Rio_Grande_Sul_7, String dj_Rave_Rio_Grande_Sul_8, String dj_Rave_Rio_Grande_Sul_9, String dj_Rave_Rio_Grande_Sul_10,
                                 String dj_Rave_Rio_Grande_Sul_11, String dj_Rave_Rio_Grande_Sul_12, String comprar_Rave_Rio_Grande_Sul, String pagina_Rave_Rio_Grande_Sul, final String instagram_Rave_Rio_Grande_Sul){


        progress_bar_Topo_Da_Rave_Rio_Grande_Sul.setVisibility(View.VISIBLE);

        textView_Rave_Rio_Grande_Sul.setText(titulo_Rave_Rio_Grande_Sul);
        textView_Rave_Data_Rio_Grande_Sul.setText(data_Rave_Rio_Grande_Sul);
        textView_Rave_Local_Rio_Grande_Sul.setText(local_Rave_Rio_Grande_Sul);
        textView_Rave_Destalhes_Rio_Grande_Sul.setText(detalhes_Rave_Rio_Grande_Sul);
        textView_Aniversariante_Rave_Rio_Grande_Sul.setText(aniversariante_Rave_Rio_Grande_Sula);

        textView_Dj_Rave_Rio_Grande_Sul_1.setText(dj_Rave_Rio_Grande_Sul_1);
        textView_Dj_Rave_Rio_Grande_Sul_2.setText(dj_Rave_Rio_Grande_Sul_2);
        textView_Dj_Rave_Rio_Grande_Sul_3.setText(dj_Rave_Rio_Grande_Sul_3);
        textView_Dj_Rave_Rio_Grande_Sul_4.setText(dj_Rave_Rio_Grande_Sul_4);
        textView_Dj_Rave_Rio_Grande_Sul_5.setText(dj_Rave_Rio_Grande_Sul_5);
        textView_Dj_Rave_Rio_Grande_Sul_6.setText(dj_Rave_Rio_Grande_Sul_6);
        textView_Dj_Rave_Rio_Grande_Sul_7.setText(dj_Rave_Rio_Grande_Sul_7);
        textView_Dj_Rave_Rio_Grande_Sul_8.setText(dj_Rave_Rio_Grande_Sul_8);
        textView_Dj_Rave_Rio_Grande_Sul_9.setText(dj_Rave_Rio_Grande_Sul_9);
        textView_Dj_Rave_Rio_Grande_Sul_10.setText(dj_Rave_Rio_Grande_Sul_10);
        textView_Dj_Rave_Rio_Grande_Sul_11.setText(dj_Rave_Rio_Grande_Sul_11);
        textView_Dj_Rave_Rio_Grande_Sul_12.setText(dj_Rave_Rio_Grande_Sul_12);

        textView_Rave_Comprar_Rio_Grande_Sul.setText(comprar_Rave_Rio_Grande_Sul);
        textView_Rave_Acessar_Pagina_Rio_Grande_Sul.setText(pagina_Rave_Rio_Grande_Sul);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagem_Rave_Rio_Grande_Sul).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Topo_Da_Rave_Rio_Grande_Sul.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Da_Rave_Rio_Grande_Sul);

        //------------------------------------------Clicks-----------------------------------------------

        cardView_Acessar_Instagram_Rave_Rio_Grande_Sul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse(instagram_Rave_Rio_Grande_Sul);
                Intent lauchBrowser = new Intent(Intent.ACTION_VIEW, uri);
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

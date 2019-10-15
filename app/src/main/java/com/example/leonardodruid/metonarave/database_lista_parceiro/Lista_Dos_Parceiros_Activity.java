package com.example.leonardodruid.metonarave.database_lista_parceiro;

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

public class Lista_Dos_Parceiros_Activity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;

    //Variavéis de ouvintes

    private DatabaseReference reference_database;

    private Parceiro parceiro;

    private boolean firebaseOffline = false;

    //Variáveis do layout

    CardView cardView_Lista_Dos_Parceiros_CardView, cardView_Acessar_Instagram_Do_Parceiro;
    ImageView imageView_Foto_Do_Parceiro, imageView_Trabalhos_Dos_Parceiros_1, imageView_Trabalhos_Dos_Parceiros_2;
    ProgressBar progress_bar_imagem_Do_Topo_Parceiro, progress_bar_Do_Trabalho_Dos_Parceiros_1, progress_bar_Do_Trabalho_Dos_Parceiros_2;
    TextView textView_Nome_Do_Parceiro, textView_Descricao_Do_Parceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__dos__parceiros_);

        //----------------Botao de voltar-------------------------
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_home_inicio_24dp);
        //------------------------------------------------------------------------------------
        cardView_Acessar_Instagram_Do_Parceiro = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Do_Parceiro);

        imageView_Foto_Do_Parceiro = (ImageView)findViewById(R.id.imageView_Foto_Do_Parceiro);
        imageView_Trabalhos_Dos_Parceiros_1 = (ImageView)findViewById(R.id.imageView_Trabalhos_Dos_Parceiros_1);
        imageView_Trabalhos_Dos_Parceiros_2 = (ImageView)findViewById(R.id.imageView_Trabalhos_Dos_Parceiros_2);

        progress_bar_imagem_Do_Topo_Parceiro = (ProgressBar)findViewById(R.id.progress_bar_imagem_Do_Topo_Parceiro);
        progress_bar_Do_Trabalho_Dos_Parceiros_1 = (ProgressBar)findViewById(R.id.progress_bar_Do_Trabalho_Dos_Parceiros_1);
        progress_bar_Do_Trabalho_Dos_Parceiros_2 = (ProgressBar)findViewById(R.id.progress_bar_Do_Trabalho_Dos_Parceiros_2);

        textView_Nome_Do_Parceiro = (TextView)findViewById(R.id.textView_Nome_Do_Parceiro);
        textView_Descricao_Do_Parceiro = (TextView)findViewById(R.id.textView_Descricao_Do_Parceiro);

        //------------------------------Chamando o Firebase--------------------------------

        database = FirebaseDatabase.getInstance();

        parceiro = getIntent().getParcelableExtra("parceiro");

        ativarFirebaseOffline();

    }

    //-------------------------Eventos de Clicks---------------------------------
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



    //-----------------------------------------Ouvindo o Banco de Dados--------------------------

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Parceiros").child(parceiro.getId()).child("DadosDoParceiro");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    //-----------Preparando para chamar no Banco de dados---------------
                    String imagemTopo = dataSnapshot.child("urlimagemTopo").getValue(String.class);
                    String nome = dataSnapshot.child("nome").getValue(String.class);
                    String descricao = dataSnapshot.child("descricao").getValue(String.class);
                    String imagemTrabalho_1 = dataSnapshot.child("imagemTrabalho_1").getValue(String.class);
                    String imagemTrabalho_2 = dataSnapshot.child("imagemTrabalho_2").getValue(String.class);
                    String instagram = dataSnapshot.child("linkInstagram").getValue(String.class);

                    //-------------Selecionar Aonde cada intem ira ficar-------------------

                    atualizarLayout(imagemTopo, nome, descricao, imagemTrabalho_1, imagemTrabalho_2, instagram);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addValueEventListener(valueEventListener);
        }

    }

    //-----------------------------------ATUALIZANDO LAYOUT--------------------------

    private void atualizarLayout(String imagemTopo, String nome, String descricao, String imagemTrabalho_1, String imagemTrabalho_2, final String instagram){

        //-------------------------ProgresBar------------------------------------
        progress_bar_imagem_Do_Topo_Parceiro.setVisibility(View.VISIBLE);
        progress_bar_Do_Trabalho_Dos_Parceiros_1.setVisibility(View.VISIBLE);
        progress_bar_Do_Trabalho_Dos_Parceiros_2.setVisibility(View.VISIBLE);

        //------------------Textos-------------------------------------------------
        textView_Nome_Do_Parceiro.setText(nome);
        textView_Descricao_Do_Parceiro.setText(descricao);

        //-------------------Eventos de clicks----------------------
        cardView_Acessar_Instagram_Do_Parceiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uriUrl = Uri.parse(instagram);
                Intent IniciarInstagram = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(IniciarInstagram);

            }
        });

        //---------------------------IMAGENS-------------------------------------


        Glide.with(getBaseContext()).load(imagemTopo).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_imagem_Do_Topo_Parceiro.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(imageView_Foto_Do_Parceiro);

        //---------------------------IMAGENS-------------------------------------

        Glide.with(getBaseContext()).load(imagemTrabalho_1).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Do_Trabalho_Dos_Parceiros_1.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Trabalhos_Dos_Parceiros_1);

        //---------------------------IMAGENS-------------------------------------

        Glide.with(getBaseContext()).load(imagemTrabalho_2).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Do_Trabalho_Dos_Parceiros_2.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Trabalhos_Dos_Parceiros_2);





    }


    @Override
    public void onClick(View v) {

    }

    //----------------------------------------Ciclo de vida----------------------------


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

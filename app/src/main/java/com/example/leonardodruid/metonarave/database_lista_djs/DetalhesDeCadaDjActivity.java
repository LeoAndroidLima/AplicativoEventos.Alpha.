package com.example.leonardodruid.metonarave.database_lista_djs;

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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetalhesDeCadaDjActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private ValueEventListener valueEventListener;

    private boolean firebaseOffline = false;

    //Variaveis para o ouvinte

    private DatabaseReference reference_Database;

    private Dj dj;

    ImageView imageView_Lista_Dos_Djs_Topo, imageView_Evento_Dos_Djs_1, imageView_Evento_Dos_Djs_2;
    ProgressBar progress_bar_Foto_Topo_Do_Dj, progress_bar_Do_Eventos_Dos_Djs_1, progress_bar_Do_Eventos_Dos_Djs_2;
    TextView textView_Lista_Dos_Djs_Nome, textView_Lista_Dos_Djs_Vertente, textView_Lista_Dos_Djs_Descricao;
    CardView cardView_Acessar_SoundCloud_Do_Dj, cardView_Acessar_Instagram_Do_Dj, cardView_Acessar_Email_Do_Dj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_de_cada_dj);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //---------------------------INSTANCIANDO ITENS-----------------------------------

        imageView_Lista_Dos_Djs_Topo = (ImageView)findViewById(R.id.imageView_Lista_Dos_Djs_Topo);
        imageView_Evento_Dos_Djs_1 = (ImageView)findViewById(R.id.imageView_Evento_Dos_Djs_1);
        imageView_Evento_Dos_Djs_2 = (ImageView)findViewById(R.id.imageView_Evento_Dos_Djs_2);

        progress_bar_Foto_Topo_Do_Dj = (ProgressBar)findViewById(R.id.progress_bar_Foto_Topo_Do_Dj);
        progress_bar_Do_Eventos_Dos_Djs_1 = (ProgressBar)findViewById(R.id.progress_bar_Do_Eventos_Dos_Djs_1);
        progress_bar_Do_Eventos_Dos_Djs_2 = (ProgressBar)findViewById(R.id.progress_bar_Do_Eventos_Dos_Djs_2);

        textView_Lista_Dos_Djs_Nome = (TextView)findViewById(R.id.textView_Lista_Dos_Djs_Nome);
        textView_Lista_Dos_Djs_Vertente = (TextView)findViewById(R.id.textView_Lista_Dos_Djs_Vertente);
        textView_Lista_Dos_Djs_Descricao = (TextView)findViewById(R.id.textView_Lista_Dos_Djs_Descricao);

        cardView_Acessar_SoundCloud_Do_Dj = (CardView)findViewById(R.id.cardView_Acessar_SoundCloud_Do_Dj);
        cardView_Acessar_Instagram_Do_Dj = (CardView)findViewById(R.id.cardView_Acessar_Instagram_Do_Dj);
        cardView_Acessar_Email_Do_Dj = (CardView)findViewById(R.id.cardView_Acessar_Email_Do_Dj);

        //--------------------------------INSTANCIANDO VARIVEL FIREBASE--------------------

        ativarFirebaseOffline();
        database = FirebaseDatabase.getInstance();
        dj = getIntent().getParcelableExtra("dj");

    }

    //------------------------------Botão da Toolbar-------------------------------


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

    //-------------------------------Escutando o Firebase--------------------------

    private void ouvinte(){

        reference_Database = database.getReference().child("BD").child("Djs").child(dj.getId()).child("DadosDoDj");

        if (valueEventListener == null){

            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String imagemTopo = dataSnapshot.child("imagemTopo").getValue(String.class);
                    String imagemEvento1 = dataSnapshot.child("imagemEvento1").getValue(String.class);
                    String imagemEvento2 = dataSnapshot.child("imagemEvento2").getValue(String.class);

                    String nomeDj = dataSnapshot.child("nomeDj").getValue(String.class);
                    String vertenteDj = dataSnapshot.child("vertenteDj").getValue(String.class);
                    String descricaoDj = dataSnapshot.child("descricaoDj").getValue(String.class);

                    String soundcloudDj = dataSnapshot.child("soundcloudDj").getValue(String.class);
                    String instagramDj = dataSnapshot.child("instagramDj").getValue(String.class);
                    String emailDj = dataSnapshot.child("emailDj").getValue(String.class);

                    atualizarLayou(imagemTopo, imagemEvento1, imagemEvento2, nomeDj, vertenteDj, descricaoDj, soundcloudDj, instagramDj, emailDj);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            reference_Database.addValueEventListener(valueEventListener);
        }

    }

    private void atualizarLayou(String imagemTopo, String imagemEvento1, String imagemEvento2,
                                String nomeDj, String vertenteDj, String descricaoDj,
                                final String soundcloudDj, final String instagramDj, final String emailDj){

        //-------------------PROGRESS BAR-------------------------------

        progress_bar_Foto_Topo_Do_Dj.setVisibility(View.VISIBLE);
        progress_bar_Do_Eventos_Dos_Djs_1.setVisibility(View.VISIBLE);
        progress_bar_Do_Eventos_Dos_Djs_2.setVisibility(View.VISIBLE);

        //-----------------TEXT VIEW-------------------------------------------

        textView_Lista_Dos_Djs_Nome.setText(nomeDj);
        textView_Lista_Dos_Djs_Vertente.setText(vertenteDj);
        textView_Lista_Dos_Djs_Descricao.setText(descricaoDj);

        //--------------------------Eventos de Click----------------------------------

        cardView_Acessar_SoundCloud_Do_Dj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uriSound = Uri.parse(soundcloudDj);
                Intent abrirBrowserSound = new Intent(Intent.ACTION_VIEW,uriSound);
                startActivity(abrirBrowserSound);

            }
        });

        cardView_Acessar_Instagram_Do_Dj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uriInstagram = Uri.parse(instagramDj);
                Intent abrirBrowserInsta = new Intent(Intent.ACTION_VIEW,uriInstagram);
                startActivity(abrirBrowserInsta);
            }
        });

        cardView_Acessar_Email_Do_Dj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailDj});
                email.putExtra(Intent.EXTRA_SUBJECT, "App ManheToNaRave");

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Escolha um APP para enviar o e-mail: "));

            }
        });

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagemTopo).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Foto_Topo_Do_Dj.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Lista_Dos_Djs_Topo);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagemEvento1).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Do_Eventos_Dos_Djs_1.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Evento_Dos_Djs_1);

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(getBaseContext()).load(imagemEvento2).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                progress_bar_Do_Eventos_Dos_Djs_2.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
                .fitCenter()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(Target.SIZE_ORIGINAL)).into(imageView_Evento_Dos_Djs_2);


    }

    @Override
    protected void onStart() {
        super.onStart();

        ouvinte();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reference_Database !=null){

            reference_Database.removeEventListener(valueEventListener);

        }
    }
}

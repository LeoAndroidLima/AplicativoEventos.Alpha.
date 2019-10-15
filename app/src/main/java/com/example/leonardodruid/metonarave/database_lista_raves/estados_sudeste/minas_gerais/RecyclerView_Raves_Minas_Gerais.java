package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.minas_gerais;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Raves_Minas_Gerais extends RecyclerView.Adapter<RecyclerView_Raves_Minas_Gerais.ViewHolder> {

    private Context context;
    private List<Raves_Minas_Gerais> raves_minas_geraiss;
    private Click_Raves_Minas_Gerais click_raves_minas_gerais;

    public RecyclerView_Raves_Minas_Gerais(Context context, List<Raves_Minas_Gerais> raves_minas_geraiss, Click_Raves_Minas_Gerais click_raves_minas_gerais){

        this.context = context;
        this.raves_minas_geraiss = raves_minas_geraiss;
        this.click_raves_minas_gerais = click_raves_minas_gerais;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_de_minas_gerais, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Minas_Gerais.ViewHolder viewHolder, int i) {

        final Raves_Minas_Gerais raves_minas_gerais = raves_minas_geraiss.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Minas_Gerais.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Minas_Gerais.setText(raves_minas_gerais.getNome());
        viewHolder.textView_Data_Das_Rave_Minas_Gerais.setText(raves_minas_gerais.getData());
        viewHolder.textView_Localizacao_Das_Raves_Minas_Gerais.setText(raves_minas_gerais.getLocalizacao());

        Glide.with(context).load(raves_minas_gerais.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Minas_Gerais.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Minas_Gerais);

        //------------------------------------------Clicks-----------------------------------------------


        viewHolder.cardView_Lista_Das_Raves_Minas_Gerais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_raves_minas_gerais.click_Raves_Minas_Gerais(raves_minas_gerais);

            }
        });

    }

    @Override
    public int getItemCount() {
        return raves_minas_geraiss.size();


    }

    public interface Click_Raves_Minas_Gerais{

        void click_Raves_Minas_Gerais(Raves_Minas_Gerais raves_minas_gerais);


    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_Lista_Das_Raves_Minas_Gerais;
        TextView textView_Nome_Das_Raves_Minas_Gerais, textView_Data_Das_Rave_Minas_Gerais, textView_Localizacao_Das_Raves_Minas_Gerais;
        ImageView imageView_Imagem_Das_Raves_Minas_Gerais;
        ProgressBar progress_bar_Carregamento_Das_Raves_Minas_Gerais;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Minas_Gerais = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Minas_Gerais);

            textView_Nome_Das_Raves_Minas_Gerais = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Minas_Gerais);
            textView_Data_Das_Rave_Minas_Gerais = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Minas_Gerais);
            textView_Localizacao_Das_Raves_Minas_Gerais = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Minas_Gerais);

            imageView_Imagem_Das_Raves_Minas_Gerais = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Minas_Gerais);
            progress_bar_Carregamento_Das_Raves_Minas_Gerais = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Minas_Gerais);


        }
    }
}

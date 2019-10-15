package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.parana;

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

public class RecyclerView_Raves_Parana extends RecyclerView.Adapter<RecyclerView_Raves_Parana.ViewHolder> {

    private Context context;
    private List<Raves_Parana> raves_paranas;
    private Click_Raves_Parana click_raves_parana;

    public RecyclerView_Raves_Parana(Context context, List<Raves_Parana> raves_paranas, Click_Raves_Parana click_raves_parana){

        this.context = context;
        this.raves_paranas = raves_paranas;
        this.click_raves_parana = click_raves_parana;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_do_parana, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Parana.ViewHolder viewHolder, int i) {

        final Raves_Parana raves_parana = raves_paranas.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Parana.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Parana.setText(raves_parana.getNome());
        viewHolder.textView_Data_Das_Rave_Parana.setText(raves_parana.getData());
        viewHolder.textView_Localizacao_Das_Raves_Parana.setText(raves_parana.getLocalizacao());

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(context).load(raves_parana.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Parana.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Parana);

        //------------------------------------------Clicks-----------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Parana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_raves_parana.click_Raves_Parana(raves_parana);

            }
        });


    }

    @Override
    public int getItemCount() {
        return raves_paranas.size();
    }

    public interface Click_Raves_Parana{

        void click_Raves_Parana(Raves_Parana raves_parana);




    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView_Lista_Das_Raves_Parana;
        TextView textView_Nome_Das_Raves_Parana, textView_Data_Das_Rave_Parana, textView_Localizacao_Das_Raves_Parana;
        ImageView imageView_Imagem_Das_Raves_Parana;
        ProgressBar progress_bar_Carregamento_Das_Raves_Parana;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Parana = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Parana);

            textView_Nome_Das_Raves_Parana = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Parana);
            textView_Data_Das_Rave_Parana = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Parana);
            textView_Localizacao_Das_Raves_Parana = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Parana);

            imageView_Imagem_Das_Raves_Parana = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Parana);

            progress_bar_Carregamento_Das_Raves_Parana = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Parana);


        }
    }


}

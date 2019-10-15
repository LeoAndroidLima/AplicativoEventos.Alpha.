package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.santa_catarina;

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

public class RecyclerView_Raves_Santa_Catarina extends RecyclerView.Adapter<RecyclerView_Raves_Santa_Catarina.ViewHolder> {

    private Context context;
    private List<Raves_Santa_Catarina> raves_santa_catarinas;
    private Click_Rave_Santa_Catarina click_rave_santa_catarina;


    public RecyclerView_Raves_Santa_Catarina (Context context, List<Raves_Santa_Catarina> raves_santa_catarinas, Click_Rave_Santa_Catarina click_rave_santa_catarina){

        this.context = context;
        this.raves_santa_catarinas = raves_santa_catarinas;
        this.click_rave_santa_catarina = click_rave_santa_catarina;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_de_santa_catarina, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Santa_Catarina.ViewHolder viewHolder, int i) {

        final Raves_Santa_Catarina raves_santa_catarina = raves_santa_catarinas.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Santa_Catarina.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Santa_Catarina.setText(raves_santa_catarina.getNome());
        viewHolder.textView_Data_Das_Rave_Santa_Catarina.setText(raves_santa_catarina.getData());
        viewHolder.textView_Localizacao_Das_Raves_Santa_Catarina.setText(raves_santa_catarina.getLocalizacao());

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(context).load(raves_santa_catarina.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Santa_Catarina.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Santa_Catarina);

        //------------------------------------------Clicks-----------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Santa_Catarina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_rave_santa_catarina.click_Rave_Santa_Catarina(raves_santa_catarina);

            }
        });

    }

    @Override
    public int getItemCount() {
        return raves_santa_catarinas.size();
    }

    public interface Click_Rave_Santa_Catarina{

        void click_Rave_Santa_Catarina(Raves_Santa_Catarina raves_santa_catarina);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView_Lista_Das_Raves_Santa_Catarina;
        TextView textView_Nome_Das_Raves_Santa_Catarina, textView_Data_Das_Rave_Santa_Catarina, textView_Localizacao_Das_Raves_Santa_Catarina;
        ImageView imageView_Imagem_Das_Raves_Santa_Catarina;
        ProgressBar progress_bar_Carregamento_Das_Raves_Santa_Catarina;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Santa_Catarina = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Santa_Catarina);

            textView_Nome_Das_Raves_Santa_Catarina = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Santa_Catarina);
            textView_Data_Das_Rave_Santa_Catarina = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Santa_Catarina);
            textView_Localizacao_Das_Raves_Santa_Catarina = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Santa_Catarina);

            imageView_Imagem_Das_Raves_Santa_Catarina = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Santa_Catarina);

            progress_bar_Carregamento_Das_Raves_Santa_Catarina = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Santa_Catarina);

        }
    }

}

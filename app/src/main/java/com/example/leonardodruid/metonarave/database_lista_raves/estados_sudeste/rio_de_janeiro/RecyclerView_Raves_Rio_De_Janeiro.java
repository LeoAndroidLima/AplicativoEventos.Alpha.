package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.rio_de_janeiro;

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

public class RecyclerView_Raves_Rio_De_Janeiro extends RecyclerView.Adapter<RecyclerView_Raves_Rio_De_Janeiro.ViewHolder> {

    private Context context;
    private List<Raves_Rio_De_Janeiro> raves_rio_de_janeiros;
    private Click_Rave_Rio_De_Janeiro click_rave_rio_de_janeiro;

    public RecyclerView_Raves_Rio_De_Janeiro(Context context, List<Raves_Rio_De_Janeiro> raves_rio_de_janeiros, Click_Rave_Rio_De_Janeiro click_rave_rio_de_janeiro){

        this.context = context;
        this.raves_rio_de_janeiros = raves_rio_de_janeiros;
        this.click_rave_rio_de_janeiro = click_rave_rio_de_janeiro;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_do_rio_de_janeiro, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Rio_De_Janeiro.ViewHolder viewHolder, int i) {

        final Raves_Rio_De_Janeiro raves_rio_de_janeiro = raves_rio_de_janeiros.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Rio_De_Janeiro.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Rio_De_Janeiro.setText(raves_rio_de_janeiro.getNome());
        viewHolder.textView_Data_Das_Rave_Santa_Rio_De_Janeiro.setText(raves_rio_de_janeiro.getData());
        viewHolder.textView_Localizacao_Das_Raves_Rio_De_Janeiro.setText(raves_rio_de_janeiro.getLocalizacao());

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(context).load(raves_rio_de_janeiro.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Rio_De_Janeiro.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Rio_De_Janeiro);

        //------------------------------------------Clicks-----------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Rio_De_Janeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                click_rave_rio_de_janeiro.click_rave_rio_de_janeiro(raves_rio_de_janeiro);

            }
        });


    }

    @Override
    public int getItemCount() {
        return raves_rio_de_janeiros.size();
    }

    public interface Click_Rave_Rio_De_Janeiro{

        void click_rave_rio_de_janeiro(Raves_Rio_De_Janeiro raves_rio_de_janeiro);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView_Lista_Das_Raves_Rio_De_Janeiro;
        TextView textView_Nome_Das_Raves_Rio_De_Janeiro, textView_Data_Das_Rave_Santa_Rio_De_Janeiro, textView_Localizacao_Das_Raves_Rio_De_Janeiro;
        ImageView imageView_Imagem_Das_Raves_Rio_De_Janeiro;
        ProgressBar progress_bar_Carregamento_Das_Raves_Rio_De_Janeiro;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Rio_De_Janeiro = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Rio_De_Janeiro);

            textView_Nome_Das_Raves_Rio_De_Janeiro = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Rio_De_Janeiro);
            textView_Data_Das_Rave_Santa_Rio_De_Janeiro = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Santa_Rio_De_Janeiro);
            textView_Localizacao_Das_Raves_Rio_De_Janeiro = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Rio_De_Janeiro);

            imageView_Imagem_Das_Raves_Rio_De_Janeiro = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Rio_De_Janeiro);

            progress_bar_Carregamento_Das_Raves_Rio_De_Janeiro = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Rio_De_Janeiro);


        }
    }
}

package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.rio_grande_sul;

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

public class RecyclerView_Raves_Rio_Grande_Sul extends RecyclerView.Adapter<RecyclerView_Raves_Rio_Grande_Sul.ViewHolder> {

    private Context context;
    private List<Raves_Rio_Grande_Sul> raves_rio_grande_suls;
    private Click_Rave_Rio_Grande_Sul click_rave_rio_grande_sul;

    public RecyclerView_Raves_Rio_Grande_Sul(Context context, List<Raves_Rio_Grande_Sul> raves_rio_grande_suls, Click_Rave_Rio_Grande_Sul click_rave_rio_grande_sul){

        this.context = context;
        this.raves_rio_grande_suls = raves_rio_grande_suls;
        this.click_rave_rio_grande_sul = click_rave_rio_grande_sul;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_rio_grande_sul, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Rio_Grande_Sul.ViewHolder viewHolder, int i) {

        final Raves_Rio_Grande_Sul raves_rio_grande_sul = raves_rio_grande_suls.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Rio_Grande_Sul.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Rio_Grande_Sul.setText(raves_rio_grande_sul.getNome());
        viewHolder.textView_Data_Das_Rave_Rio_Grande_Sul.setText(raves_rio_grande_sul.getData());
        viewHolder.textView_Localizacao_Das_Raves_Rio_Grande_Sul.setText(raves_rio_grande_sul.getLocalizacao());

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(context).load(raves_rio_grande_sul.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Rio_Grande_Sul.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Rio_Grande_Sul);

        //------------------------------------------Clicks-----------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Rio_Grande_Sul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_rave_rio_grande_sul.click_Rave_Rio_Grande_Sul(raves_rio_grande_sul);

            }
        });



    }

    @Override
    public int getItemCount() {
        return raves_rio_grande_suls.size();
    }

    public interface Click_Rave_Rio_Grande_Sul{

        void click_Rave_Rio_Grande_Sul(Raves_Rio_Grande_Sul raves_rio_grande_sul);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_Lista_Das_Raves_Rio_Grande_Sul;
        TextView textView_Nome_Das_Raves_Rio_Grande_Sul, textView_Data_Das_Rave_Rio_Grande_Sul, textView_Localizacao_Das_Raves_Rio_Grande_Sul;
        ImageView imageView_Imagem_Das_Raves_Rio_Grande_Sul;
        ProgressBar progress_bar_Carregamento_Das_Raves_Rio_Grande_Sul;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Rio_Grande_Sul = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Rio_Grande_Sul);

            textView_Nome_Das_Raves_Rio_Grande_Sul = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Rio_Grande_Sul);
            textView_Data_Das_Rave_Rio_Grande_Sul = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Rio_Grande_Sul);
            textView_Localizacao_Das_Raves_Rio_Grande_Sul = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Rio_Grande_Sul);

            imageView_Imagem_Das_Raves_Rio_Grande_Sul = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Rio_Grande_Sul);

            progress_bar_Carregamento_Das_Raves_Rio_Grande_Sul = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Rio_Grande_Sul);




        }
    }

}

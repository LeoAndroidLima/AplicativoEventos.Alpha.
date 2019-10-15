package com.example.leonardodruid.metonarave.database_lista_djs;

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

public class RecyclerView_Dj extends RecyclerView.Adapter<RecyclerView_Dj.ViewHolder> {

    private Context context;
    private List<Dj> djs;
    private ClickDj clickDj;


    public RecyclerView_Dj(Context context, List<Dj> djs, ClickDj clickDj){

        this.context = context;
        this.djs = djs;
        this.clickDj = clickDj;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_conteudo_quais_djs_recyclerview, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Dj dj = djs.get(i);

        viewHolder.progress_bar_Foto_Dj_RecyclerView.setVisibility(View.VISIBLE);

        viewHolder.textView_Dj_Item_Nome.setText(dj.getNome());
        viewHolder.textView_Dj_Item_Estilo.setText(dj.getEstilo());

        Glide.with(context).load(dj.getUrlimagemDj()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Foto_Dj_RecyclerView.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Dj_Item_Imagem);


        viewHolder.cardView_Dj_Item_CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickDj.click_Dj(dj);

            }
        });


    }

    @Override
    public int getItemCount() {
        return djs.size();

    }

    public interface ClickDj{

        void click_Dj(Dj dj);

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView_Dj_Item_CardView;
        ImageView imageView_Dj_Item_Imagem;
        ProgressBar progress_bar_Foto_Dj_RecyclerView;
        TextView textView_Dj_Item_Nome, textView_Dj_Item_Estilo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Dj_Item_CardView = (CardView)itemView.findViewById(R.id.cardView_Dj_Item_CardView);

            imageView_Dj_Item_Imagem = (ImageView) itemView.findViewById(R.id.imageView_Dj_Item_Imagem);

            progress_bar_Foto_Dj_RecyclerView = (ProgressBar)itemView.findViewById(R.id.progress_bar_Foto_Dj_RecyclerView);

            textView_Dj_Item_Nome = (TextView)itemView.findViewById(R.id.textView_Dj_Item_Nome);
            textView_Dj_Item_Estilo = (TextView)itemView.findViewById(R.id.textView_Dj_Item_Estilo);
        }
    }
}

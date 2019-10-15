package com.example.leonardodruid.metonarave.database_lista_parceiro;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

public class RecyclerView_ListaParceiro extends RecyclerView.Adapter<RecyclerView_ListaParceiro.ViewHolder> {

    private Context context;
    private List<Parceiro> parceiros;
    private ClickParceiro clickParceiro;

    //------------------------Contrutores---------------------------------------------
    public RecyclerView_ListaParceiro(Context context, List<Parceiro> parceiros, ClickParceiro clickParceiro){

        this.context = context;
        this.parceiros = parceiros;
        this.clickParceiro = clickParceiro;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.database_lista_parceiro_conteudo_recycleview,viewGroup,false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        final Parceiro parceiro = parceiros.get(i);

        viewHolder.progress_bar_Parceiro_Item_Image.setVisibility(View.VISIBLE);

        viewHolder.textView_nome.setText(parceiro.getNome());


        Glide.with(context).load(parceiro.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Parceiro_Item_Image.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.image_Parceiro);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickParceiro.click_Parceiro(parceiro);


            }
        });


    }

    @Override
    public int getItemCount() {
        return parceiros.size();

    }


    public interface ClickParceiro{

        void click_Parceiro(Parceiro parceiro);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textView_nome;
        ImageView image_Parceiro;
        ProgressBar progress_bar_Parceiro_Item_Image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView_Parceiro_Item_CardView);
            textView_nome = (TextView)itemView.findViewById(R.id.textView_Parceiro_Item_Nome);
            image_Parceiro = (ImageView)itemView.findViewById(R.id.imageView_Parceiro_Item_Image);
            progress_bar_Parceiro_Item_Image = (ProgressBar)itemView.findViewById(R.id.progress_bar_Parceiro_Item_Image);



        }
    }
}

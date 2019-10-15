package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.sao_paulo;

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

public class RecyclerView_Raves_Sao_Paulo extends RecyclerView.Adapter<RecyclerView_Raves_Sao_Paulo.ViewHolder> {

    private Context context;
    private List<Raves_Sao_Paulo> raves_sao_paulos;
    private Click_Rave_Sao_Paulo click_rave_sao_paulo;

    public RecyclerView_Raves_Sao_Paulo(Context context, List<Raves_Sao_Paulo> raves_sao_paulos, Click_Rave_Sao_Paulo click_rave_sao_paulo){

        this.context = context;
        this.raves_sao_paulos = raves_sao_paulos;
        this.click_rave_sao_paulo = click_rave_sao_paulo;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_de_sao_paulo,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Sao_Paulo.ViewHolder viewHolder, int i) {

        final Raves_Sao_Paulo raves_sao_paulo = raves_sao_paulos.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Sao_Paulo.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Sao_Paulo.setText(raves_sao_paulo.getNome());
        viewHolder.textView_Data_Das_Rave_Sao_Paulo.setText(raves_sao_paulo.getData());
        viewHolder.textView_Localizacao_Das_Raves_Sao_Paulo.setText(raves_sao_paulo.getLocalizacao());

        //----------------------------------------IMAGENS--------------------------------

        Glide.with(context).load(raves_sao_paulo.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Sao_Paulo.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Sao_Paulo);

        //------------------------------------------Clicks-----------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Sao_Paulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                click_rave_sao_paulo.click_Rave_Sao_Paulo(raves_sao_paulo);

            }
        });

    }

    @Override
    public int getItemCount() {
        return raves_sao_paulos.size();
    }

    public interface Click_Rave_Sao_Paulo{

        void click_Rave_Sao_Paulo(Raves_Sao_Paulo raves_sao_paulo);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView_Lista_Das_Raves_Sao_Paulo;
        private TextView textView_Nome_Das_Raves_Sao_Paulo, textView_Data_Das_Rave_Sao_Paulo, textView_Localizacao_Das_Raves_Sao_Paulo;
        private ImageView imageView_Imagem_Das_Raves_Sao_Paulo;
        private ProgressBar progress_bar_Carregamento_Das_Raves_Sao_Paulo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Sao_Paulo = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Sao_Paulo);

            textView_Nome_Das_Raves_Sao_Paulo = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Sao_Paulo);
            textView_Data_Das_Rave_Sao_Paulo = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Sao_Paulo);
            textView_Localizacao_Das_Raves_Sao_Paulo = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Sao_Paulo);

            imageView_Imagem_Das_Raves_Sao_Paulo = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Sao_Paulo);

            progress_bar_Carregamento_Das_Raves_Sao_Paulo = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Sao_Paulo);



        }
    }

}

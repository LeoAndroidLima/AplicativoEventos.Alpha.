package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.espirito_santo;

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

public class RecyclerView_Raves_Espirito_Santo extends RecyclerView.Adapter<RecyclerView_Raves_Espirito_Santo.ViewHolder> {

    private Context context;
    private List<Rave_Espirito_Santo> rave_espirito_santos;
    private Click_Raves_Espirito_Santo click_raves_espirito_santo;

    public RecyclerView_Raves_Espirito_Santo(Context context, List<Rave_Espirito_Santo> rave_espirito_santos, Click_Raves_Espirito_Santo click_raves_espirito_santo){

        this.context = context;
        this.rave_espirito_santos = rave_espirito_santos;
        this.click_raves_espirito_santo = click_raves_espirito_santo;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_raves_de_espirito_santo, viewGroup, false);

        ViewHolder holder = new ViewHolder(view);

        view.setTag(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView_Raves_Espirito_Santo.ViewHolder viewHolder, int i) {

        final Rave_Espirito_Santo rave_espirito_santo = rave_espirito_santos.get(i);

        viewHolder.progress_bar_Carregamento_Das_Raves_Espirito_Santo.setVisibility(View.VISIBLE);

        viewHolder.textView_Nome_Das_Raves_Espirito_Santo.setText(rave_espirito_santo.getNome());
        viewHolder.textView_Data_Das_Rave_Santa_Espirito_Santo.setText(rave_espirito_santo.getData());
        viewHolder.textView_Localizacao_Das_Raves_Espirito_Santo.setText(rave_espirito_santo.getLocalizacao());

        //---------------------------------------------Imagens------------------------------

        Glide.with(context).load(rave_espirito_santo.getUrlimagem()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                viewHolder.progress_bar_Carregamento_Das_Raves_Espirito_Santo.setVisibility(View.GONE);

                return false;
            }
        }).apply(new RequestOptions()
        .fitCenter()
        .format(DecodeFormat.PREFER_ARGB_8888)
        .override(Target.SIZE_ORIGINAL)).into(viewHolder.imageView_Imagem_Das_Raves_Espirito_Santo);

        //-------------------------------------------Click------------------------------------------------

        viewHolder.cardView_Lista_Das_Raves_Espirito_Santo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click_raves_espirito_santo.click_Raves_Espirito_Santo(rave_espirito_santo);


            }
        });



    }

    @Override
    public int getItemCount() {
        return rave_espirito_santos.size();
    }

    public interface Click_Raves_Espirito_Santo{

        void click_Raves_Espirito_Santo(Rave_Espirito_Santo rave_espirito_santo);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_Lista_Das_Raves_Espirito_Santo;

        TextView textView_Nome_Das_Raves_Espirito_Santo, textView_Data_Das_Rave_Santa_Espirito_Santo, textView_Localizacao_Das_Raves_Espirito_Santo;

        ImageView imageView_Imagem_Das_Raves_Espirito_Santo;

        ProgressBar progress_bar_Carregamento_Das_Raves_Espirito_Santo;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView_Lista_Das_Raves_Espirito_Santo = (CardView)itemView.findViewById(R.id.cardView_Lista_Das_Raves_Espirito_Santo);

            textView_Nome_Das_Raves_Espirito_Santo = (TextView)itemView.findViewById(R.id.textView_Nome_Das_Raves_Espirito_Santo);
            textView_Data_Das_Rave_Santa_Espirito_Santo = (TextView)itemView.findViewById(R.id.textView_Data_Das_Rave_Santa_Espirito_Santo);
            textView_Localizacao_Das_Raves_Espirito_Santo = (TextView)itemView.findViewById(R.id.textView_Localizacao_Das_Raves_Espirito_Santo);

            imageView_Imagem_Das_Raves_Espirito_Santo = (ImageView)itemView.findViewById(R.id.imageView_Imagem_Das_Raves_Espirito_Santo);

            progress_bar_Carregamento_Das_Raves_Espirito_Santo = (ProgressBar)itemView.findViewById(R.id.progress_bar_Carregamento_Das_Raves_Espirito_Santo);

        }
    }

}

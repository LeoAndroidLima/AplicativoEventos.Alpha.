package com.example.leonardodruid.metonarave.database_lista_raves;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.leonardodruid.metonarave.R;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.ListaRavesEstadosSulActivity;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.ListaRavesEstadosSulDesteActivity;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaRegioesBrasilFragment extends Fragment {


    public ListaRegioesBrasilFragment() {
        // Required empty public constructor
    }

    Button button_Raves_Regiao_Sul, button_Raves_Regiao_SulDeste;
    private boolean firebaseOffline = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista_regioes_brasil, container, false);

        ativarFirebaseOffline();

        button_Raves_Regiao_Sul = (Button)rootView.findViewById(R.id.button_Raves_Regiao_Sul);
        button_Raves_Regiao_SulDeste = (Button)rootView.findViewById(R.id.button_Raves_Regiao_SulDeste);

        button_Raves_Regiao_Sul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSul = new Intent(getActivity(), ListaRavesEstadosSulActivity.class);
                startActivity(intentSul);



            }
        });

        button_Raves_Regiao_SulDeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentSulDeste = new Intent(getActivity(), ListaRavesEstadosSulDesteActivity.class);
                startActivity(intentSulDeste);

            }
        });




        return rootView;
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


}

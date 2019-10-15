package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.minas_gerais;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leonardodruid.metonarave.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Lista_Raves_Minas_GeraisFragment extends Fragment implements RecyclerView_Raves_Minas_Gerais.Click_Raves_Minas_Gerais {


    public Lista_Raves_Minas_GeraisFragment() {
    }

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference reference_database;
    private ChildEventListener childEventListener;

    private boolean firebaseOffline = false;

    private RecyclerView_Raves_Minas_Gerais recyclerView_raves_minas_gerais;

    private List<Raves_Minas_Gerais> raves_minas_geraiss = new ArrayList<Raves_Minas_Gerais>();
    private List<String> keys = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista__raves__minas__gerais, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_Lista_Raves_Minas_Gerais);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return rootView;
    }

    private void iniciarRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_minas_gerais = new RecyclerView_Raves_Minas_Gerais(getActivity(), raves_minas_geraiss, this);

        recyclerView.setAdapter(recyclerView_raves_minas_gerais);


    }

    @Override
    public void click_Raves_Minas_Gerais(Raves_Minas_Gerais raves_minas_gerais) {

        Intent intent = new Intent(getActivity(), Raves_Minas_GeraisActivity.class);
        intent.putExtra("raves_minas_gerais", raves_minas_gerais);
        startActivity(intent);

        //Toast.makeText(getActivity(), "Nome" + raves_minas_gerais.getNome(),Toast.LENGTH_LONG).show();

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

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Raves_Minas_Gerais");

        if (childEventListener == null){


            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Raves_Minas_Gerais raves_minas_gerais = dataSnapshot.getValue(Raves_Minas_Gerais.class);

                    raves_minas_gerais.setId(key);

                    raves_minas_geraiss.add(raves_minas_gerais);

                    recyclerView_raves_minas_gerais.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Raves_Minas_Gerais raves_minas_gerais = dataSnapshot.getValue(Raves_Minas_Gerais.class);

                    raves_minas_gerais.setId(key);

                    raves_minas_geraiss.set(index, raves_minas_gerais);

                    recyclerView_raves_minas_gerais.notifyDataSetChanged();


                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    raves_minas_geraiss.remove(index);

                    keys.remove(index);

                    recyclerView_raves_minas_gerais.notifyItemRemoved(index);
                    recyclerView_raves_minas_gerais.notifyItemChanged(index, raves_minas_geraiss.size());

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            reference_database.addChildEventListener(childEventListener);
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        ouvinte();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (childEventListener != null){

            reference_database.removeEventListener(childEventListener);

        }

    }
}

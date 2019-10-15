package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.sao_paulo;


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
public class Lista_Raves_Sao_PauloFragment extends Fragment implements RecyclerView_Raves_Sao_Paulo.Click_Rave_Sao_Paulo {


    public Lista_Raves_Sao_PauloFragment() {
    }

    private RecyclerView recyclerView;
    private RecyclerView_Raves_Sao_Paulo recyclerView_raves_sao_paulo;

    private FirebaseDatabase database;
    private DatabaseReference reference_database;
    private ChildEventListener childEventListener;

    private boolean firebaseOffline = false;

    private List<Raves_Sao_Paulo> raves_sao_paulos = new ArrayList<Raves_Sao_Paulo>();
    private List<String> keys = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_lista__raves__sao__paulo, container, false);

        recyclerView = (RecyclerView)RootView.findViewById(R.id.recyclerView_Lista_Raves_Sao_Paulo);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return RootView;
    }

    private void iniciarRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_sao_paulo = new RecyclerView_Raves_Sao_Paulo(getActivity(), raves_sao_paulos, this);

        recyclerView.setAdapter(recyclerView_raves_sao_paulo);

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

    @Override
    public void click_Rave_Sao_Paulo(Raves_Sao_Paulo raves_sao_paulo) {

        Intent intent = new Intent(getActivity(),Raves_De_Sao_PauloActivity.class);
        intent.putExtra("raves_sao_paulo", raves_sao_paulo);
        startActivity(intent);

    }

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Raves_Sao_Paulo");

        if (childEventListener == null) {


            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Raves_Sao_Paulo raves_sao_paulo = dataSnapshot.getValue(Raves_Sao_Paulo.class);

                    raves_sao_paulo.setId(key);

                    raves_sao_paulos.add(raves_sao_paulo);

                    recyclerView_raves_sao_paulo.notifyDataSetChanged();


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Raves_Sao_Paulo raves_sao_paulo = dataSnapshot.getValue(Raves_Sao_Paulo.class);

                    raves_sao_paulo.setId(key);

                    raves_sao_paulos.set(index, raves_sao_paulo);

                    recyclerView_raves_sao_paulo.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    raves_sao_paulos.remove(index);

                    keys.remove(index);

                    recyclerView_raves_sao_paulo.notifyItemRemoved(index);
                    recyclerView_raves_sao_paulo.notifyItemChanged(index, raves_sao_paulos.size());

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

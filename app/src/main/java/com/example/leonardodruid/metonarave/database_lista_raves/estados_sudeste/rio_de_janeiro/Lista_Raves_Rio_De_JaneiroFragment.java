package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.rio_de_janeiro;


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
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.espirito_santo.Raves_Espirito_SantoActivity;
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
public class Lista_Raves_Rio_De_JaneiroFragment extends Fragment implements RecyclerView_Raves_Rio_De_Janeiro.Click_Rave_Rio_De_Janeiro {


    public Lista_Raves_Rio_De_JaneiroFragment() {
    }

    private RecyclerView recyclerView;
    private RecyclerView_Raves_Rio_De_Janeiro recyclerView_raves_rio_de_janeiro;

    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private DatabaseReference reference_database;

    private boolean firebaseOffline = false;

    private List<Raves_Rio_De_Janeiro> raves_rio_de_janeiros = new ArrayList<Raves_Rio_De_Janeiro>();
    private List<String> keys = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_lista__raves__rio__de__janeiro, container, false);

        recyclerView = (RecyclerView)viewRoot.findViewById(R.id.recyclerView_Lista_Raves_Rio_De_Janeiro);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return viewRoot;
    }

    private void iniciarRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_rio_de_janeiro = new RecyclerView_Raves_Rio_De_Janeiro(getActivity(), raves_rio_de_janeiros, this);

        recyclerView.setAdapter(recyclerView_raves_rio_de_janeiro);

    }

    @Override
    public void click_rave_rio_de_janeiro(Raves_Rio_De_Janeiro raves_rio_de_janeiro) {

        Intent intent = new Intent(getActivity(), Raves_Rio_De_JaneiroActivity.class);
        intent.putExtra("raves_rio_de_janeiro", raves_rio_de_janeiro);
        startActivity(intent);


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

        reference_database = database.getReference().child("BD").child("Raves_Rio_De_Janeiro");

        if (childEventListener == null){


            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Raves_Rio_De_Janeiro raves_rio_de_janeiro = dataSnapshot.getValue(Raves_Rio_De_Janeiro.class);

                    raves_rio_de_janeiro.setId(key);

                    raves_rio_de_janeiros.add(raves_rio_de_janeiro);

                    recyclerView_raves_rio_de_janeiro.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Raves_Rio_De_Janeiro raves_rio_de_janeiro = dataSnapshot.getValue(Raves_Rio_De_Janeiro.class);

                    raves_rio_de_janeiro.setId(key);

                    raves_rio_de_janeiros.set(index, raves_rio_de_janeiro);

                    recyclerView_raves_rio_de_janeiro.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    raves_rio_de_janeiros.remove(index);

                    keys.remove(index);

                    recyclerView_raves_rio_de_janeiro.notifyItemRemoved(index);
                    recyclerView_raves_rio_de_janeiro.notifyItemChanged(index, raves_rio_de_janeiros.size());

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

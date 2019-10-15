package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.espirito_santo;


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
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.rio_de_janeiro.Raves_Rio_De_JaneiroActivity;
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
public class Lista_Raves_Espirito_SantoFragment extends Fragment implements RecyclerView_Raves_Espirito_Santo.Click_Raves_Espirito_Santo {


    public Lista_Raves_Espirito_SantoFragment() {
    }

    private RecyclerView recyclerView;
    private FirebaseDatabase database;

    private RecyclerView_Raves_Espirito_Santo recyclerView_raves_espirito_santo;
    private List<Rave_Espirito_Santo> rave_espirito_santos = new ArrayList<Rave_Espirito_Santo>();

    //-------------Ler informaçoes que estão no banco de dados

    private ChildEventListener childEventListener;
    private DatabaseReference reference_database;
    private List<String> keys = new ArrayList<String>();

    private boolean firebaseOffline = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista__raves__espirito__santo, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_Lista_Raves_Espirito_Santo);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return rootView;

    }

    private void iniciarRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_espirito_santo = new RecyclerView_Raves_Espirito_Santo(getActivity(),rave_espirito_santos, this);

        recyclerView.setAdapter(recyclerView_raves_espirito_santo);

    }


    @Override
    public void click_Raves_Espirito_Santo(Rave_Espirito_Santo rave_espirito_santo) {

        Intent intent = new Intent(getActivity(), Raves_Espirito_SantoActivity.class);
        intent.putExtra("raves_espirito_santo", rave_espirito_santo);
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

        reference_database = database.getReference().child("BD").child("Raves_Espirito_Santo");

        if (childEventListener == null) {

            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Rave_Espirito_Santo rave_espirito_santo = dataSnapshot.getValue(Rave_Espirito_Santo.class);

                    rave_espirito_santo.setId(key);

                    rave_espirito_santos.add(rave_espirito_santo);

                    recyclerView_raves_espirito_santo.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Rave_Espirito_Santo rave_espirito_santo = dataSnapshot.getValue(Rave_Espirito_Santo.class);

                    rave_espirito_santo.setId(key);

                    rave_espirito_santos.set(index, rave_espirito_santo);

                    recyclerView_raves_espirito_santo.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    rave_espirito_santos.remove(index);

                    keys.remove(index);

                    recyclerView_raves_espirito_santo.notifyItemRemoved(index);
                    recyclerView_raves_espirito_santo.notifyItemChanged(index, rave_espirito_santos.size());

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

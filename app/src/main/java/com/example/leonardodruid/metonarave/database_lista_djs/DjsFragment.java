package com.example.leonardodruid.metonarave.database_lista_djs;


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
import android.widget.LinearLayout;
import android.widget.Toast;

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
public class DjsFragment extends Fragment implements RecyclerView_Dj.ClickDj{


    public DjsFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private FirebaseDatabase database;

    private RecyclerView_Dj recyclerView_dj;
    private List<Dj> djs = new ArrayList<Dj>();

    private boolean firebaseOffline = false;


    //Ler informações que estão no banco de dados

    private ChildEventListener childEventListener;

    private DatabaseReference reference_database;

    private List<String> keys = new ArrayList<String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView_Dj = inflater.inflate(R.layout.fragment_djs, container, false);

        recyclerView = (RecyclerView) rootView_Dj.findViewById(R.id.recyclerView_Database_Dj);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        // Inflate the layout for this fragment
        return rootView_Dj;
    }

    private void iniciarRecyclerView(){

        LinearLayoutManager layoutManager_Djs = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager_Djs);

        recyclerView_dj = new RecyclerView_Dj(getActivity(),djs,this);

        recyclerView.setAdapter(recyclerView_dj);

    }

    //-------------------------------------------Click dos itens------------------------------------

    @Override
    public void click_Dj(Dj dj) {

        Intent intent = new Intent(getActivity(),DetalhesDeCadaDjActivity.class);
        intent.putExtra("dj", dj);
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


    //-------------------------------------------OUVINTE-----------------------------------------------

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Djs");

        if (childEventListener == null) {


            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Dj dj = dataSnapshot.getValue(Dj.class);

                    dj.setId(key);

                    djs.add(dj);

                    recyclerView_dj.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Dj dj = dataSnapshot.getValue(Dj.class);

                    dj.setId(key);

                    djs.set(index, dj);

                    recyclerView_dj.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    djs.remove(index);

                    keys.remove(index);

                    recyclerView_dj.notifyItemRemoved(index);
                    recyclerView_dj.notifyItemChanged(index, djs.size());

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

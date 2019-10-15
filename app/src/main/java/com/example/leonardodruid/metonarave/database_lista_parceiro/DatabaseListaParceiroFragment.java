package com.example.leonardodruid.metonarave.database_lista_parceiro;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class DatabaseListaParceiroFragment extends Fragment implements RecyclerView_ListaParceiro.ClickParceiro {


    public DatabaseListaParceiroFragment() {

    }

    //--------------------------------Criando variavel--------------------------------
    private RecyclerView recyclerView;
    private FirebaseDatabase database;

    private RecyclerView_ListaParceiro recyclerView_listaParceiro;
    private List<Parceiro> parceiros = new ArrayList<Parceiro>();

    //Ler informação que estão no banco de dados
    private ChildEventListener childEventListener;

    private DatabaseReference reference_database;

    private List<String> keys = new ArrayList<String>();
    private boolean firebaseOffline = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView_Parceiro = inflater.inflate(R.layout.fragment_database_lista_parceiro, container, false);

        recyclerView = (RecyclerView) rootView_Parceiro.findViewById(R.id.recyclerView_Database_Parceiro);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        // Inflate the layout for this fragment

        return rootView_Parceiro;
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



    private void iniciarRecyclerView(){



        //StaggeredGridLayoutManager layoutManager_Parceiro = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView_listaParceiro = new RecyclerView_ListaParceiro(getActivity(),parceiros,this);

        recyclerView.setAdapter(recyclerView_listaParceiro);

    }

    @Override
    public void click_Parceiro(Parceiro parceiro) {

        Intent intent = new Intent(getActivity(),Lista_Dos_Parceiros_Activity.class);

        intent.putExtra("parceiro",parceiro);

        startActivity(intent);

    }

    //---------------------------------Ouvinte--------------------------------------------

    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Parceiros");

        if (childEventListener == null){

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               String key = dataSnapshot.getKey();

               keys.add(key);

               Parceiro parceiro = dataSnapshot.getValue(Parceiro.class);

               parceiro.setId(key);

               parceiros.add(parceiro);

               recyclerView_listaParceiro.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               String key = dataSnapshot.getKey();

               int index = keys.indexOf(key);

               Parceiro parceiro = dataSnapshot.getValue(Parceiro.class);

               parceiro.setId(key);

               parceiros.set(index,parceiro);

               recyclerView_listaParceiro.notifyDataSetChanged();


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();

                int index = keys.indexOf(key);

                parceiros.remove(index);

                keys.remove(index);

                recyclerView_listaParceiro.notifyItemRemoved(index);
                recyclerView_listaParceiro.notifyItemChanged(index,parceiros.size());

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

    //------------------------------------------Ciclos de Vida------------------------------

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

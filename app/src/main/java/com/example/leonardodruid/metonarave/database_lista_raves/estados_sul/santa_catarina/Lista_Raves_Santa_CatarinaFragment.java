package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.santa_catarina;


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
public class Lista_Raves_Santa_CatarinaFragment extends Fragment implements RecyclerView_Raves_Santa_Catarina.Click_Rave_Santa_Catarina {


    public Lista_Raves_Santa_CatarinaFragment() {
    }

    private RecyclerView recyclerView;
    private RecyclerView_Raves_Santa_Catarina recyclerView_raves_santa_catarina;

    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private DatabaseReference reference_database;

    private List<Raves_Santa_Catarina> raves_santa_catarinas = new ArrayList<Raves_Santa_Catarina>();
    private List<String> keys = new ArrayList<String>();

    private boolean firebaseOffline = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_lista__raves__santa__catarina, container, false);

        recyclerView = (RecyclerView)viewRoot.findViewById(R.id.recyclerView_Lista_Raves_Santa_Catarina);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return viewRoot;
    }

    private void iniciarRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_santa_catarina = new RecyclerView_Raves_Santa_Catarina(getActivity(),raves_santa_catarinas, this);

        recyclerView.setAdapter(recyclerView_raves_santa_catarina);

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
    public void click_Rave_Santa_Catarina(Raves_Santa_Catarina raves_santa_catarina) {

        Intent intent = new Intent(getActivity(),Raves_De_Santa_CatarinaActivity.class);

        intent.putExtra("raves_santa_catarina", raves_santa_catarina);

        startActivity(intent);


    }
    private void ouvinte(){

        reference_database = database.getReference().child("BD").child("Raves_Santa_Catarina");

        if (childEventListener == null) {


            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Raves_Santa_Catarina raves_santa_catarina = dataSnapshot.getValue(Raves_Santa_Catarina.class);

                    raves_santa_catarina.setId(key);

                    raves_santa_catarinas.add(raves_santa_catarina);

                    recyclerView_raves_santa_catarina.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Raves_Santa_Catarina raves_santa_catarina = dataSnapshot.getValue(Raves_Santa_Catarina.class);

                    raves_santa_catarina.setId(key);

                    raves_santa_catarinas.set(index,raves_santa_catarina);

                    recyclerView_raves_santa_catarina.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    raves_santa_catarinas.remove(index);

                    keys.remove(index);

                    recyclerView_raves_santa_catarina.notifyItemRemoved(index);
                    recyclerView_raves_santa_catarina.notifyItemChanged(index, raves_santa_catarinas.size());

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

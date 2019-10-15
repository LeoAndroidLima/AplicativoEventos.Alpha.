package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.rio_grande_sul;


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
public class Lista_Raves_Rio_Grande_SulFragment extends Fragment implements RecyclerView_Raves_Rio_Grande_Sul.Click_Rave_Rio_Grande_Sul {


    public Lista_Raves_Rio_Grande_SulFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView_Raves_Rio_Grande_Sul recyclerView_raves_rio_grande_sul;

    private FirebaseDatabase database;
    private ChildEventListener childEventListener;
    private DatabaseReference reference_database;

    private List<Raves_Rio_Grande_Sul> raves_rio_grande_suls = new ArrayList<Raves_Rio_Grande_Sul>();
    private List<String> keys = new ArrayList<String>();

    private boolean firebaseOffline = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista__raves__rio__grande__sul, container, false);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_Lista_Raves_Rio_Grande_Sul);

        database = FirebaseDatabase.getInstance();

        ativarFirebaseOffline();
        iniciarRecyclerView();

        return rootView;
    }

    private void iniciarRecyclerView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView_raves_rio_grande_sul = new RecyclerView_Raves_Rio_Grande_Sul(getActivity(), raves_rio_grande_suls, this);

        recyclerView.setAdapter(recyclerView_raves_rio_grande_sul);


    }

    //------------------------------------------------Click dos itens-----------------------

    @Override
    public void click_Rave_Rio_Grande_Sul(Raves_Rio_Grande_Sul raves_rio_grande_sul) {

        Intent intent = new Intent(getActivity(), Raves_Rio_Grande_SulActivity.class);
        intent.putExtra("raves_rio_grande_sul", raves_rio_grande_sul);
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

        reference_database = database.getReference().child("BD").child("Raves_Rio_Grande_Sul");

        if (childEventListener == null){



            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    keys.add(key);

                    Raves_Rio_Grande_Sul raves_rio_grande_sul = dataSnapshot.getValue(Raves_Rio_Grande_Sul.class);

                    raves_rio_grande_sul.setId(key);

                    raves_rio_grande_suls.add(raves_rio_grande_sul);

                    recyclerView_raves_rio_grande_sul.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    Raves_Rio_Grande_Sul raves_rio_grande_sul =dataSnapshot.getValue(Raves_Rio_Grande_Sul.class);

                    raves_rio_grande_suls.set(index, raves_rio_grande_sul);

                    recyclerView_raves_rio_grande_sul.notifyDataSetChanged();

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    String key = dataSnapshot.getKey();

                    int index = keys.indexOf(key);

                    raves_rio_grande_suls.remove(index);

                    keys.remove(index);

                    recyclerView_raves_rio_grande_sul.notifyItemRemoved(index);
                    recyclerView_raves_rio_grande_sul.notifyItemChanged(index, raves_rio_grande_suls.size());



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

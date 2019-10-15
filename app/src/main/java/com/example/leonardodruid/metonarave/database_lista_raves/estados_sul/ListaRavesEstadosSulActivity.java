package com.example.leonardodruid.metonarave.database_lista_raves.estados_sul;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.leonardodruid.metonarave.R;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.parana.Lista_Raves_ParanaFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.rio_grande_sul.Lista_Raves_Rio_Grande_SulFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sul.santa_catarina.Lista_Raves_Santa_CatarinaFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class ListaRavesEstadosSulActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout_Lista_Raves_Sul;
    private ViewPager viewpager_Lista_Raves_Sul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_raves_estados_sul);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        smartTabLayout_Lista_Raves_Sul = (SmartTabLayout)findViewById(R.id.smartTabLayout_Lista_Raves_Sul);
        viewpager_Lista_Raves_Sul = (ViewPager)findViewById(R.id.viewpager_Lista_Raves_Sul);

        //--------------------------------Trazendo os fragmentos ao layout----------------------

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                .add("PR", Lista_Raves_ParanaFragment.class)
                .add("SC", Lista_Raves_Santa_CatarinaFragment.class)
                .add("RS", Lista_Raves_Rio_Grande_SulFragment.class)
                .create()
        );

        viewpager_Lista_Raves_Sul.setAdapter(adapter);
        smartTabLayout_Lista_Raves_Sul.setViewPager(viewpager_Lista_Raves_Sul);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }


    }
}

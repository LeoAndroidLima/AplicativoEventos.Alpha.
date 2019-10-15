package com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.leonardodruid.metonarave.R;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.espirito_santo.Lista_Raves_Espirito_SantoFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.rio_de_janeiro.Lista_Raves_Rio_De_JaneiroFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.minas_gerais.Lista_Raves_Minas_GeraisFragment;
import com.example.leonardodruid.metonarave.database_lista_raves.estados_sudeste.sao_paulo.Lista_Raves_Sao_PauloFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class ListaRavesEstadosSulDesteActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout_Lista_Raves_SulDeste;
    private ViewPager viewpager_Lista_Raves_SulDeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_raves_estados_sul_deste);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        smartTabLayout_Lista_Raves_SulDeste = (SmartTabLayout)findViewById(R.id.smartTabLayout_Lista_Raves_SulDeste);
        viewpager_Lista_Raves_SulDeste = (ViewPager)findViewById(R.id.viewpager_Lista_Raves_SulDeste);

        //--------------------------------Trazendo os fragmentos ao layout----------------------

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                .add("SP", Lista_Raves_Sao_PauloFragment.class)
                .add("RJ", Lista_Raves_Rio_De_JaneiroFragment.class)
                .add("MG", Lista_Raves_Minas_GeraisFragment.class)
                .add("ES", Lista_Raves_Espirito_SantoFragment.class)
                .create()
        );

        viewpager_Lista_Raves_SulDeste.setAdapter(adapter);
        smartTabLayout_Lista_Raves_SulDeste.setViewPager(viewpager_Lista_Raves_SulDeste);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

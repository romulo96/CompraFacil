package com.example.romulo.comprafacil;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.romulo.comprafacil.DAO.ProdutoDAO;
import com.example.romulo.comprafacil.MODEL.Produto;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private static ListView list;
    private ViewPager mViewPager;
    private static  List<String> listm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


      /*  ProdutoDAO PD = new ProdutoDAO(this);
        ListView list = (ListView) findViewById(R.id.ListProdutos);
        ArrayAdapter<String> adapter;
        List<String> listm = new ArrayList<>();
       // listm = PD.buscarProduto();
        listm.add("oi");
        adapter = new ArrayAdapter<String>(CadastroActivity.this, android.R.layout.simple_list_item_1, listm);
        list.setAdapter(adapter);*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }
    @Override
    protected void onResume(){
        super.onResume();
        ProdutoDAO PD = new ProdutoDAO(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    //Comentar se quiser que não apareceça menu inflater sair
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tela2_cadastro) {
            //Menu inflater para sair da aplicação na tela de lista de produtos
            Intent  it = new Intent(getApplicationContext(), MainActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.putExtra("SAIR", true);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                View rootView = inflater.inflate(R.layout.fragment_secoes, container, false);

                return rootView;
            }else {
                View rootView = inflater.inflate(R.layout.fragment_cadastro, container, false);

                ProdutoDAO PD = new ProdutoDAO(getActivity());
                list = (ListView) rootView.findViewById(R.id.ListProdutos);
                List<Produto> produtos=PD.busca(); PD.close();
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(getActivity(), android.R.layout.simple_list_item_1, produtos);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> lista, View produto_s, int i, long l) {
                    Produto produto=(Produto) list.getItemAtPosition(i);
                        Intent intentgoAlterar=new Intent(getActivity(),CadastraProdutoActivity.class);
                        intentgoAlterar.putExtra("produto",produto);
                        startActivity(intentgoAlterar);
                    }
                    });
                registerForContextMenu(list);
                return rootView;
            }

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        //Botão para deletar
        MenuItem menuContexto;

        menuContexto= menu.add("Deletar");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto produto = (Produto) list.getItemAtPosition(info.position);
                ProdutoDAO PD = new ProdutoDAO(CadastroActivity.this);
                PD.deletar(produto);
                List<Produto> produtos=PD.busca(); PD.close();
                ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(CadastroActivity.this, android.R.layout.simple_list_item_1, produtos);
                list.setAdapter(adapter);
                PD.close();
                return false;
            }
        });
        menuContexto = menu.add("Exibir Foto");
        menuContexto.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto produto = (Produto) list.getItemAtPosition(info.position);
                ProdutoDAO PD = new ProdutoDAO(CadastroActivity.this);
                Intent letgol=new Intent(CadastroActivity.this,ExibeFotoActivity.class);
                letgol.putExtra("produto",produto);
                startActivity(letgol);
                return false;
            }
        });

    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PRODUTOS";
                case 1:
                    return "SEÇÕES";

            }
            return null;
        }
    }

    // CHAMAR TELA CADASTRO PRODUTO
    public void startactivity_cadastra_produto(View view){
        Intent activity_cadastra_produto = new Intent(this, CadastraProdutoActivity.class);
        startActivity(activity_cadastra_produto);
    }

    // CHAMAR TELA SEÇÃO
    public void startactivity_cadastra_secao(View view){
        Intent activity_cadastra_secao = new Intent(this, CadastraSecaoActivity.class);
        startActivity(activity_cadastra_secao);
    }

}

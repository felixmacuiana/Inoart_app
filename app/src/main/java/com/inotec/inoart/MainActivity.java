package com.inotec.inoart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    //Toolbar toolbar;

    FloatingActionButton fab;
    androidx.appcompat.widget.Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer );
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId==R.id.bottom_explorar){
                    openFragment( new ExplorarFragment());
                    return true;
                }else if(itemId==R.id.bottom_eventos){
                    openFragment( new EventosFragment());
                    return true;
                }else if(itemId==R.id.bottom_rede_contatos){
                    openFragment( new RedeContatoFragment());
                    return true;
                } else if(itemId==R.id.bottom_perfil){
                    openFragment( new PerfilFragment());
                    return true;
                }
                return false;
            }
        });

        fragmentManager= getSupportFragmentManager();
        openFragment(new InicioFragment());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Publique sua obra", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.bottom_perfil){
            openFragment(new PerfilFragment());
        } else if (itemId == R.id.bottom_eventos) {
            openFragment(new EventosFragment());
        } else if (itemId == R.id.bottom_explorar) {
            openFragment(new ExplorarFragment());
        } else if (itemId == R.id.bottom_perfil_portofolio) {
            openFragment(new PortofolioFragment());
        }else if (itemId == R.id.bottom_definicoes) {
            openFragment(new DefinicoesFragment());
        } else if (itemId == R.id.bottom_rede_contatos) {
            openFragment(new RedeContatoFragment());
        } else if (itemId == R.id.bottom_ajuda_suporte) {
            openFragment(new AjudaSuporteFragment());
        } else if (itemId == R.id.bottom_inicio) {
            openFragment(new InicioFragment());
        } else if (itemId == R.id.bottom_perfil_portofolio) {
            openFragment(new PortofolioFragment());
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openFragment (Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
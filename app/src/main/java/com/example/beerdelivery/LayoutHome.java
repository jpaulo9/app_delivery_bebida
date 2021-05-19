package com.example.beerdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.beerdelivery.dados.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LayoutHome extends AppCompatActivity {

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_home);

        BottomNavigationView barra = findViewById(R.id.botom_navegate);
        barra.setOnNavigationItemSelectedListener(selectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.navegue, new Fragment_home()).commit();

        Bundle daddos = getIntent().getExtras();
        if ((daddos!= null) && (daddos.containsKey("Logado"))) {
            usuario = (Usuario) daddos.getSerializable("Logado");

        }else {
            Intent intent = new Intent(LayoutHome.this, Login.class);
            startActivity(intent);
            finish();
        }


    }

    BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;

            switch (menuItem.getItemId()){

                case R.id.id_home:
                    fragment = new Fragment_home();
                    break;
                case R.id.id_bebidas:
                    fragment = new Fragment_bebidas();
                    break;
                case R.id.id_pedidos:
                    fragment = new Fragment_pedidos();
                    break;
                case R.id.id_conta:
                    fragment = new Fragment_conta();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.navegue, fragment).commit();
            return true;
        }
    };
}
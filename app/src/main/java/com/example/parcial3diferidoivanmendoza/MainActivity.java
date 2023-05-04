package com.example.parcial3diferidoivanmendoza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.parcial3diferidoivanmendoza.Clases.FragmentoCarrosClientes;
import com.example.parcial3diferidoivanmendoza.Clases.FragmentoClientes;
import com.example.parcial3diferidoivanmendoza.Clases.FragmentoVehiculos;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnNavigator;
    Fragment selectFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavigator = findViewById( R.id.btnNav );
        btnNavigator.setOnNavigationItemSelectedListener( ( BottomNavigationView.OnNavigationItemSelectedListener ) navListener );
        selectFragment = new FragmentoClientes();
        getSupportFragmentManager().beginTransaction().replace( R.id.FragmentContent, selectFragment ).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectFragment = null;

            switch ( item.getItemId() ){
                case R.id.nav_person:
                    selectFragment =new FragmentoClientes();
                    break;

                case R.id.nav_car:
                    selectFragment =new FragmentoVehiculos();
                    break;

                case R.id.nav_carPerson:
                    selectFragment =new FragmentoCarrosClientes();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace( R.id.FragmentContent, selectFragment ).commit();
            return true;
        }
    };
}
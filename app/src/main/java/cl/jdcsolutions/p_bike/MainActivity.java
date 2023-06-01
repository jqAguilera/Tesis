package cl.jdcsolutions.p_bike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cl.jdcsolutions.p_bike.Fragments.BicisFragment;
import cl.jdcsolutions.p_bike.Fragments.Home;
import cl.jdcsolutions.p_bike.Fragments.PerfilFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction transaction;
    Fragment HomeFragment, PerfilFragment, BicisFragment;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        HomeFragment = new Home();
        PerfilFragment = new PerfilFragment();
        BicisFragment = new BicisFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorPrincipal,HomeFragment).commit();

        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.contenedorPrincipal,HomeFragment).commit();

            }
        });

        ImageButton btnPerfil = (ImageButton) findViewById(R.id.btnPerfil);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.contenedorPrincipal,PerfilFragment).commit();
            }
        });

        ImageButton btnBicis = (ImageButton) findViewById(R.id.btnBicis);

        btnBicis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorPrincipal,BicisFragment).commit();

            }
        });




    }
}
package cl.jdcsolutions.p_bike;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import cl.jdcsolutions.p_bike.Fragments.FragmentRegister1;

public class RegisterApp extends AppCompatActivity {

    Fragment Register1;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_app);

        Register1 = new FragmentRegister1();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorRegister,Register1).commit();





    }
}
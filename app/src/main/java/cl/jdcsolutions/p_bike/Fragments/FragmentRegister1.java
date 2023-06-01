package cl.jdcsolutions.p_bike.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cl.jdcsolutions.p_bike.LoginApp;
import cl.jdcsolutions.p_bike.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegister1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegister1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAuth mAuth;

    View vista;

    FragmentTransaction transaction;

    Fragment Register1, Register2;

    Button btnSiguiente, btnAtras;
    
    EditText etCorreo, etPassword, etRepPassword;

    public FragmentRegister1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegister1.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegister1 newInstance(String param1, String param2) {
        FragmentRegister1 fragment = new FragmentRegister1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_register1, container, false);

        Register1 = new FragmentRegister1();
        Register2 = new FragmentRegister2();
        
        etCorreo = vista.findViewById(R.id.etCorreo);
        etPassword = vista.findViewById(R.id.etPassword);
        etRepPassword = vista.findViewById(R.id.etRepPassword);
        


        btnSiguiente = vista.findViewById(R.id.btnSiguiente);
        btnAtras = vista.findViewById(R.id.btnAtras);



        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userCorreo = etCorreo.getText().toString().trim();
                String userPassword = etPassword.getText().toString().trim();
                String userRepPassword = etRepPassword.getText().toString().trim();
                
                if (userCorreo.isEmpty() || userPassword.isEmpty() || userRepPassword.isEmpty()){

                    Toast.makeText(getActivity(), "Debe Completar los campos!", Toast.LENGTH_SHORT).show();
                    
                }else {

                    if (userPassword.trim().length() >= 6){

                        if (userPassword.equals(userRepPassword)){

                            registerUser(userCorreo, userPassword);


                            transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.contenedorRegister,Register2).commit();
                        } else {

                            Toast.makeText(getActivity(), "Las Contraseñas no coinciden!", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(getActivity(), "La contraseña debe tener una longitud minima de 6 caracteres", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (getActivity(), LoginApp.class);
                startActivity(intent);
            }
        });
        return vista;
    }

    private void registerUser (String Correo, String Password){

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(Correo, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error al crear Usuario!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
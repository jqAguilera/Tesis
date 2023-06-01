package cl.jdcsolutions.p_bike.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import cl.jdcsolutions.p_bike.LoginApp;
import cl.jdcsolutions.p_bike.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegister2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegister2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    View vista;

    Button btnCompletar;

    EditText etNombre, etRut, etCarrera;

    public FragmentRegister2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentRegister2.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegister2 newInstance(String param1, String param2) {
        FragmentRegister2 fragment = new FragmentRegister2();
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
        vista = inflater.inflate(R.layout.fragment_register2, container, false);
        etCarrera = vista.findViewById(R.id.etCarrera);
        etNombre = vista.findViewById(R.id.etNombre);
        etRut = vista.findViewById(R.id.etRut);



        btnCompletar = vista.findViewById(R.id.btnCompletar);
        btnCompletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userCarrera = etCarrera.getText().toString().trim();
                String userNombre = etNombre.getText().toString().trim();
                String userRut = etRut.getText().toString().trim();

                if (userNombre.isEmpty() || userCarrera.isEmpty() || userRut.isEmpty()){

                    Toast.makeText(getActivity(), "Debe Ingresar los datos!", Toast.LENGTH_SHORT).show();

                }else {

                    saveDatosUsuario(userNombre, userRut, userCarrera);

                    Toast.makeText(getActivity(), "Registro con Exito!, Inicie Sesión para continuar.", Toast.LENGTH_LONG).show();

                    Intent intent= new Intent (getActivity(), LoginApp.class);
                    startActivity(intent);

                }


            }
        });
        return vista;
    }

    private void saveDatosUsuario(String Nombre, String Rut, String Carrera){

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        String id = mAuth.getCurrentUser().getUid();

        Map<String, Object> user = new HashMap<>();

        user.put("id", id);
        user.put("nombre", Nombre);
        user.put("rut", Rut);
        user.put("carrera", Carrera);
        user.put("rol", "alumno");

        mFirestore.collection("usuarios").document(id).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error al guardar datos!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
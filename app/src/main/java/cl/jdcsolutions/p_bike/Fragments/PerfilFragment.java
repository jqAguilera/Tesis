package cl.jdcsolutions.p_bike.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import cl.jdcsolutions.p_bike.LoginApp;
import cl.jdcsolutions.p_bike.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View vista;
    Button btnCerraSesion;

    TextView tvNombre, tvRut, tvCarrera;

    String Nombre, Rut, Carrera;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        String userId = mAuth.getCurrentUser().getUid();

        DocumentReference datosUser = mFirestore.collection("usuarios").document(userId);
        datosUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot datosUser, @Nullable FirebaseFirestoreException error) {
                Nombre = datosUser.getString("nombre");
                Rut = datosUser.getString("rut");
                Carrera = datosUser.getString("carrera");
                tvNombre = vista.findViewById(R.id.tvNombre);
                tvNombre.setText(Nombre);
                tvCarrera = vista.findViewById(R.id.tvCarrera);
                tvCarrera.setText(Carrera);
                tvRut = vista.findViewById(R.id.tvRut);
                tvRut.setText(Rut);

            }
        });

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        btnCerraSesion = vista.findViewById(R.id.btnCerrarSesion);
        btnCerraSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean sesion = false;
                savePreference(sesion);
                Intent intent= new Intent (getActivity(), LoginApp.class);
                startActivity(intent);

            }
        });
        return vista;
    }

    private void savePreference(boolean sesion){

        SharedPreferences preferences = this.getActivity().getSharedPreferences("Sesion", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion", sesion);

        editor.commit();

    }
}
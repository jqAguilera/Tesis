package cl.jdcsolutions.p_bike.Fragments;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import cl.jdcsolutions.p_bike.R;
import cl.jdcsolutions.p_bike.databinding.ActivityMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseAuth mAuth;
    FirebaseFirestore mFirestore;

    String etQRtest, userName;
    EditText testQR;

    TextView tvUserName;

    // Ni idea que esa webada CONSULTAR!!

    ActivityMainBinding binding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() == null){

        }else {
            etQRtest = result.getContents();
        }
    });

    // Fin de la webada

    ImageButton btnQR;
    View vista;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        // Inflate the layout for this fragment

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        String userId = mAuth.getCurrentUser().getUid();

        DocumentReference datosUser = mFirestore.collection("usuarios").document(userId);
        datosUser.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot datosUser, @Nullable FirebaseFirestoreException error) {
                userName = datosUser.getString("nombre");
                tvUserName = vista.findViewById(R.id.tvUsername);
                tvUserName.setText(userName);

            }
        });


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        binding.getRoot();

        vista = inflater.inflate(R.layout.fragment_home, container, false);

        btnQR = vista.findViewById(R.id.btnQR);

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scanner();

                testQR = vista.findViewById(R.id.tvCantDisponible);
                testQR.setText(etQRtest);

            }

        });

        return vista;
    }

    public void scanner() {
        ScanOptions options  = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE);
        options.setPrompt("Escanear Codigo!");
        options.setCameraId(0);
        options.setOrientationLocked(false);
        options.setBeepEnabled(true);
        options.setCaptureActivity(CaptureActivityPortraint.class);
        options.setBarcodeImageEnabled(false);

        barcodeLauncher.launch(options);

    }
}
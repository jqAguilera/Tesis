package cl.jdcsolutions.p_bike;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cl.jdcsolutions.p_bike.Objetos.Bicicleta;

public class AdapterViewBicicletas  extends RecyclerView.Adapter<AdapterViewBicicletas.ViewHolderBicis> {

    ArrayList<Bicicleta> listBicis;

    public AdapterViewBicicletas(ArrayList<Bicicleta> listBicis) {
        this.listBicis = listBicis;
    }

    @NonNull
    @Override
    public AdapterViewBicicletas.ViewHolderBicis onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bicicletas, null,false);
        return new ViewHolderBicis(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewBicicletas.ViewHolderBicis holder, int position) {

        holder.crearBici(listBicis.get(position));

    }

    @Override
    public int getItemCount() {

        return listBicis.size();
    }

    public class ViewHolderBicis extends RecyclerView.ViewHolder {

        TextView tvMarca, tvColor;

        public ViewHolderBicis(@NonNull View itemView) {

            super(itemView);

            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvColor = itemView.findViewById(R.id.tvColor);
        }

        public void crearBici(Bicicleta bicicleta) {

            listBicis = new ArrayList<Bicicleta>();

        }
    }
}

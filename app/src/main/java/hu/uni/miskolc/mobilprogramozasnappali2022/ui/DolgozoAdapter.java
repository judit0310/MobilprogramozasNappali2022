package hu.uni.miskolc.mobilprogramozasnappali2022.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.uni.miskolc.mobilprogramozasnappali2022.R;
import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoDTO;

public class DolgozoAdapter extends
        RecyclerView.Adapter<DolgozoViewHolder> {

    private List<DolgozoDTO> dolgozok;
    private DolgozoKivalasztListener listener;

    public void setDolgozok(List<DolgozoDTO> dolgozok) {
        this.dolgozok = dolgozok;
    }

    public void setListener(DolgozoKivalasztListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DolgozoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.dolgozo_sor, parent,
                false);
        DolgozoViewHolder vh = new DolgozoViewHolder(layout, listener);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DolgozoViewHolder holder, int position) {
        DolgozoDTO dolgozo =
                dolgozok.get(position);
        holder.azonosito.setText(String.valueOf(dolgozo.getId()));
        holder.vezetekNev.setText(dolgozo.getVezetekNev());
        holder.keresztNev.setText(dolgozo.getKeresztNev());

    }

    @Override
    public int getItemCount() {
        return dolgozok.size();
    }
}

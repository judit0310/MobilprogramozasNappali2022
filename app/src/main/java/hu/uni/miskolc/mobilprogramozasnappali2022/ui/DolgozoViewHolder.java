package hu.uni.miskolc.mobilprogramozasnappali2022.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import hu.uni.miskolc.mobilprogramozasnappali2022.R;

public class DolgozoViewHolder
        extends RecyclerView.ViewHolder implements View.OnClickListener{

    protected TextView azonosito;
    protected TextView keresztNev;
    protected TextView vezetekNev;
    protected DolgozoKivalasztListener listener;

    public DolgozoViewHolder(@NonNull View itemView, DolgozoKivalasztListener listener) {
        super(itemView);
        this.azonosito = itemView.findViewById(R.id.azonosito);
        this.keresztNev = itemView.findViewById(R.id.keresztNev);
        this.vezetekNev = itemView.findViewById(R.id.vezetekNev);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       listener.onDolgozoClick(getAdapterPosition(),view);
        //Itt mondjuk meg, mit csináljunk, amikor rákattintottunk
    }
}

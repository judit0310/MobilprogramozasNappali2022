package hu.uni.miskolc.mobilprogramozasnappali2022.ui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import hu.uni.miskolc.mobilprogramozasnappali2022.R;

public class DolgozoViewHolder
        extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView azonosito;
    protected TextView keresztNev;
    protected TextView vezetekNev;
    protected DolgozoKivalasztListener listener;
    protected ImageButton torlesGomb;
    protected ImageButton nezesGomb;

    public DolgozoViewHolder(@NonNull View itemView, DolgozoKivalasztListener listener) {
        super(itemView);
        this.azonosito = itemView.findViewById(R.id.azonosito);
        this.keresztNev = itemView.findViewById(R.id.keresztNev);
        this.vezetekNev = itemView.findViewById(R.id.vezetekNev);
        this.torlesGomb = itemView.findViewById(R.id.torlesGomb);
        this.nezesGomb = itemView.findViewById(R.id.nezesGomb);
        this.listener = listener;
        itemView.setOnClickListener(this);
        this.torlesGomb.setOnClickListener(this);
        this.nezesGomb.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == torlesGomb) {
            listener.onDolgozoClickDelete(getAdapterPosition(), view);
        } else if(view == nezesGomb){
            listener.onDolgozoClick(getAdapterPosition(), view);
        }
        else{

            if (this.torlesGomb.getVisibility() == View.INVISIBLE) {
                this.torlesGomb.setVisibility(View.VISIBLE);
            } else {
                this.torlesGomb.setVisibility(View.INVISIBLE);
            }
        }

        //Itt mondjuk meg, mit csináljunk, amikor rákattintottunk
    }
}

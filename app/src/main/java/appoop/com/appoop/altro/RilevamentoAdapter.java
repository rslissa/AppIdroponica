package appoop.com.appoop.altro;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import appoop.com.appoop.R;
import appoop.com.appoop.modelli.Rilevamento;


public class RilevamentoAdapter extends RecyclerView.Adapter<RilevamentoAdapter.RilevamentoViewHolder>{
    private List<Rilevamento> rilevamentoList;
    private ClickListener clickListener = null;

    public RilevamentoAdapter(List<Rilevamento> rilevamentoList){
        this.rilevamentoList = rilevamentoList;
    }


    @Override
    public RilevamentoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        return new RilevamentoViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RilevamentoViewHolder rilevamentoViewHolder, int i) {
        Rilevamento m = rilevamentoList.get(i);
        rilevamentoViewHolder.showData.setText(m.dataToString ());
        rilevamentoViewHolder.showEntrata.setText("" + m.getL_entrata ());
        rilevamentoViewHolder.showUscita.setText("" + m.getL_sgrondo ());
        rilevamentoViewHolder.showEC.setText("" + m.getEC_sgrondo ());
    }

    @Override
    public int getItemCount() {
        return rilevamentoList.size();
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    public class RilevamentoViewHolder extends RecyclerView.ViewHolder{
        protected  TextView showData;
        protected  TextView showEntrata;
        protected TextView showUscita;
        protected  TextView showEC;
        protected RelativeLayout linear;
        public RilevamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            showData = itemView.findViewById(R.id.showData);
            showEntrata = itemView.findViewById(R.id.showL_entrata);
            showUscita = itemView.findViewById(R.id.showL_sgrondo);
            showEC=itemView.findViewById(R.id.showEC_sgrondo);
            linear = itemView.findViewById(R.id.linear);
            linear.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(clickListener != null)
                        clickListener.itemClicked(v, getAdapterPosition());
                    return true;
                }
            });
        }
    }
}



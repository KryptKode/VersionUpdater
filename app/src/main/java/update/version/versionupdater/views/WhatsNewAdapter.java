package update.version.versionupdater.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import update.version.versionupdater.R;
import update.version.versionupdater.api.model.WhatsNew;

public class WhatsNewAdapter extends RecyclerView.Adapter<WhatsNewAdapter.WhatsNewViewHolder> {

    private List<WhatsNew>  whatsNewList;
    private OnWhatsNewListener listener;


    public WhatsNewAdapter(OnWhatsNewListener listener) {
        whatsNewList = new ArrayList<>();
        this.listener = listener;
    }

    public void setWhatsNewList(List<WhatsNew> whatsNewList) {
        this.whatsNewList = whatsNewList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WhatsNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WhatsNewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_whats_new, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsNewViewHolder holder, int position) {
        WhatsNew whatsNew = whatsNewList.get(position);
        holder.button.setText(whatsNew.getWhatsNewMessage());
    }

    @Override
    public int getItemCount() {
        return whatsNewList.size();
    }

    class WhatsNewViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.btn)
        SupportVectorDrawablesButton button;

        public WhatsNewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn)
        public void onClick(){
            if (listener != null) {
                listener.onClick(whatsNewList.get(getAdapterPosition()));
            }
        }
    }

    public interface OnWhatsNewListener{
        void onClick(WhatsNew whatsNew);
    }
}

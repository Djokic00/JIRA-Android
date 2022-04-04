package raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DoneAdapter extends ListAdapter<Ticket, DoneAdapter.ViewHolder> {

    private final Consumer<Ticket> onImageClicked;

    public DoneAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onImageClicked) {
        super(diffCallback);
        this.onImageClicked = onImageClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_done_list, parent, false);
        return new ViewHolder(view, position -> {
            Ticket ticket = getItem(position);
            onImageClicked.accept(ticket);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position); // uzima auto na odredjenoj poziciji iz ViewModela liste
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView, Consumer<Integer> onImageClicked) {
            super(itemView);
            itemView.findViewById(R.id.ticketTypePicture).setOnClickListener(view -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onImageClicked.accept(getBindingAdapterPosition());
                }
            });
        }

        public void bind(Ticket ticket) {
            ImageView ticketType = itemView.findViewById(R.id.ticketTypePicture);
            if (ticket.getTicketType().equals("Bug")) {
                ticketType.setImageResource(R.drawable.bug_icon);
            }
            else {
                ticketType.setImageResource(R.drawable.enhancement_icon);
            }
            ((TextView) itemView.findViewById(R.id.ticketTitle)).setText(ticket.getTitle());
            ((TextView) itemView.findViewById(R.id.ticketDescription)).setText(ticket.getDescription());
            ImageView moveTicket = itemView.findViewById(R.id.moveRightTicket);
            ImageView deleteTicket = itemView.findViewById(R.id.moveBackTicket);
        }
    }
}

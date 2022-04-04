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

public class InProgressAdapter extends ListAdapter<Ticket, InProgressAdapter.ViewHolder> {

    private final Consumer<Ticket> onImageClicked;
    private final Consumer<Ticket> onMoveRightClicked;
    private final Consumer<Ticket> onMoveBack;

    public InProgressAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onImageClicked,
                       Consumer<Ticket> onMoveRightClicked, Consumer<Ticket> onMoveBack) {
        super(diffCallback);
        this.onImageClicked = onImageClicked;
        this.onMoveRightClicked = onMoveRightClicked;
        this.onMoveBack = onMoveBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_inprogress_list, parent, false);
        return new ViewHolder(view, position -> {
            Ticket ticket = getItem(position);
            onImageClicked.accept(ticket);
        }, position -> {
            Ticket ticket = getItem(position);
            onMoveRightClicked.accept(ticket);
        }, position -> {
            Ticket ticket = getItem(position);
            onMoveBack.accept(ticket);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position); // uzima auto na odredjenoj poziciji iz ViewModela liste
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView, Consumer<Integer> onImageClicked, Consumer<Integer> onMoveRightClicked,
                          Consumer<Integer> onMoveBackClicked) {
            super(itemView);
            itemView.findViewById(R.id.ticketTypePicture).setOnClickListener(view -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onImageClicked.accept(getBindingAdapterPosition());
                }
            });
            itemView.findViewById(R.id.moveRightTicket).setOnClickListener(move -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onMoveRightClicked.accept(getBindingAdapterPosition());
                }
            });

            itemView.findViewById(R.id.moveBackTicket).setOnClickListener(delete -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onMoveBackClicked.accept(getBindingAdapterPosition());
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

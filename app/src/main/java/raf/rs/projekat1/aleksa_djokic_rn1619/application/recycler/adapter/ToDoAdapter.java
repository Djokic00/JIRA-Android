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

public class ToDoAdapter extends ListAdapter<Ticket, ToDoAdapter.ViewHolder> {

    private final Consumer<Ticket> onImageClicked;
    private final Consumer<Ticket> onMoveClicked;
    private final Consumer<Ticket> onDeleteClicked;

    public ToDoAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback, Consumer<Ticket> onImageClicked,
                         Consumer<Ticket> onMoveClicked, Consumer<Ticket> onDeleteClicked) {
        super(diffCallback);
        this.onImageClicked = onImageClicked;
        this.onMoveClicked = onMoveClicked;
        this.onDeleteClicked = onDeleteClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_todo_list, parent, false);
        return new ViewHolder(view, position -> {
            Ticket ticket = getItem(position);
            onImageClicked.accept(ticket);
        }, position -> {
            Ticket ticket = getItem(position);
            onMoveClicked.accept(ticket);
        }, position -> {
            Ticket ticket = getItem(position);
            onDeleteClicked.accept(ticket);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position); // uzima auto na odredjenoj poziciji iz ViewModela liste
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView, Consumer<Integer> onImageClicked, Consumer<Integer> onMoveClicked,
                          Consumer<Integer> onDeleteClicked) {
            super(itemView);
            itemView.findViewById(R.id.ticketTypePicture).setOnClickListener(view -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onImageClicked.accept(getBindingAdapterPosition());
                }
            });
            itemView.findViewById(R.id.moveTicket).setOnClickListener(move -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onMoveClicked.accept(getBindingAdapterPosition());
                }
            });

            itemView.findViewById(R.id.deleteTicket).setOnClickListener(delete -> {
                if (getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onDeleteClicked.accept(getBindingAdapterPosition());
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
            ImageView moveTicket = itemView.findViewById(R.id.moveTicket);
            ImageView deleteTicket = itemView.findViewById(R.id.deleteTicket);
        }
    }
}

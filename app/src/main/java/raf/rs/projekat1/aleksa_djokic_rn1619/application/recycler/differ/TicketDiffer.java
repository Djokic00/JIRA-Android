package raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import org.jetbrains.annotations.NotNull;

public class TicketDiffer extends DiffUtil.ItemCallback<Ticket> {

    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NotNull Ticket newItem) {
        return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getTicketType().equals(newItem.getTicketType())
                && oldItem.getTicketPriority().equals(newItem.getTicketPriority()) && oldItem.getDescription().equals(newItem.getDescription())
                && oldItem.getNumberOfDays() == newItem.getNumberOfDays();// && oldItem.getImageView().equals(newItem.getImageView());
    }
}


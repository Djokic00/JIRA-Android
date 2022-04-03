package view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application.R;
import models.Ticket;
import models.TicketParcelable;
import recycler.adapter.ToDoAdapter;
import recycler.differ.TicketDiffer;
import view.activities.TicketDetailsActivity;
import viewmodels.TicketViewModel;

import static view.activities.TicketDetailsActivity.DETAILS_KEY;

public class ToDoTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private ToDoAdapter toDoAdapter;
    private RecyclerView recyclerView;

    public ToDoTicket() {
        super(R.layout.fragment_todo_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        initObservers();
        initRecycler();
    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.todoRv);
    }

    public void initObservers() {
        ticketViewModel.getToDoTickets().observe(getViewLifecycleOwner(), ticket -> {
            toDoAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        toDoAdapter = new ToDoAdapter(new TicketDiffer(), ticket -> {
            //Toast.makeText(getContext(), ticket.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), TicketDetailsActivity.class);
            intent.putExtra(DETAILS_KEY, new TicketParcelable(ticket.getTitle(), ticket.getDescription(),
                    ticket.getTicketType(), ticket.getTicketPriority(), ticket.getNumberOfDays(), ticket.getTicketState()));
            startActivity(intent);
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.removeTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(toDoAdapter);
    }
}

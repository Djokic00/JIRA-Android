package view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application.R;
import recycler.adapter.ToDoAdapter;
import recycler.differ.TicketDiffer;
import viewmodels.TicketViewModel;

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
            Toast.makeText(getContext(), ticket.getTitle(), Toast.LENGTH_SHORT).show();
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.removeTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(toDoAdapter);
    }
}

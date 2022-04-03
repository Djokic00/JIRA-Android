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
import recycler.adapter.InProgressAdapter;
import recycler.differ.TicketDiffer;
import viewmodels.TicketViewModel;

public class InProgressTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private InProgressAdapter inProgressAdapter;
    private RecyclerView recyclerView;

    public InProgressTicket() {
        super(R.layout.fragment_inprogress_recycler);
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
        recyclerView = view.findViewById(R.id.inProgressRv);
    }

    public void initObservers() {
        ticketViewModel.getInProgressTickets().observe(getViewLifecycleOwner(), ticket -> {
            inProgressAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        inProgressAdapter = new InProgressAdapter(new TicketDiffer(), ticket -> {
            Toast.makeText(getContext(), ticket.getTitle(), Toast.LENGTH_SHORT).show();
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.moveBackwardTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(inProgressAdapter);
    }
}

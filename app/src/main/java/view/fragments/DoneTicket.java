package view.fragments;

import android.content.Intent;
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
import recycler.adapter.DoneAdapter;
import recycler.differ.TicketDiffer;
import viewmodels.TicketViewModel;

public class DoneTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private DoneAdapter doneAdapter;
    private RecyclerView recyclerView;

    public DoneTicket() {
            super(R.layout.fragment_done_recycler);
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
        recyclerView = view.findViewById(R.id.doneRv);
    }

    public void initObservers() {
        ticketViewModel.getDoneTickets().observe(getViewLifecycleOwner(), ticket -> {
            doneAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        doneAdapter = new DoneAdapter(new TicketDiffer(), ticket -> {
            Intent intent;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(doneAdapter);
    }
}

package view.fragments;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import org.jetbrains.annotations.NotNull;
import viewmodels.TicketViewModel;

public class ToDoTicket extends Fragment {
    private TicketViewModel ticketViewModel;

    public ToDoTicket() {
        //super(R.layout.);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    public void initView(View view) {

    }

    public void initListeners() {
        ticketViewModel.getTickets().observe(getViewLifecycleOwner(), ticket -> {

        });
    }

    public void initObservers() {

    }

    public void initRecycler() {

    }
}

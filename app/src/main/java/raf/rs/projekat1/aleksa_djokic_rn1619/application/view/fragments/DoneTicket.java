package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.TicketParcelable;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.DoneAdapter;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.differ.TicketDiffer;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity.DETAILS_KEY;

public class DoneTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private DoneAdapter doneAdapter;
    private RecyclerView recyclerView;
    private EditText search;

    public DoneTicket() {
            super(R.layout.fragment_done_recycler);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        initListeners();
        initObservers();
        initRecycler();
    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.doneRv);
        search = view.findViewById(R.id.searchDone);
    }

    public void initListeners() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ticketViewModel.filterDoneTickets(editable.toString());
            }
        });
    }

    public void initObservers() {
        ticketViewModel.getDoneTickets().observe(getViewLifecycleOwner(), ticket -> {
            doneAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        doneAdapter = new DoneAdapter(new TicketDiffer(), ticket -> {
            Intent intent = new Intent(requireActivity(), TicketDetailsActivity.class);
            intent.putExtra(DETAILS_KEY, new TicketParcelable(ticket.getTitle(), ticket.getDescription(),
                    ticket.getTicketType(), ticket.getTicketPriority(), ticket.getNumberOfDays(), ticket.getId(), ticket.getTicketState()));
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(doneAdapter);
    }
}

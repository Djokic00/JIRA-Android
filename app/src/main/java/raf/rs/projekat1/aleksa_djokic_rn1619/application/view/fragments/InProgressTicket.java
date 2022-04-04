package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

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
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.TicketParcelable;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.InProgressAdapter;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.differ.TicketDiffer;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity.DETAILS_KEY;

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
            Intent intent = new Intent(getActivity(), TicketDetailsActivity.class);
            intent.putExtra(DETAILS_KEY, new TicketParcelable(ticket.getTitle(), ticket.getDescription(),
                    ticket.getTicketType(), ticket.getTicketPriority(), ticket.getNumberOfDays(), ticket.getTicketState()));
            startActivity(intent);
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.moveBackwardTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(inProgressAdapter);
    }
}

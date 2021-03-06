package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.TicketParcelable;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.InProgressAdapter;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.differ.TicketDiffer;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

import static android.app.Activity.RESULT_OK;
import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity.DETAILS_KEY;
import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity.RETURN_TO_MAIN;

public class InProgressTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private InProgressAdapter inProgressAdapter;
    private RecyclerView recyclerView;
    private EditText search;
    private Ticket ticket;

    public InProgressTicket() {
        super(R.layout.fragment_inprogress_recycler);
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
        recyclerView = view.findViewById(R.id.inProgressRv);
        search = view.findViewById(R.id.searchInProgress);
    }

    public void initListeners() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ticketViewModel.filterInProgressTickets(editable.toString());
            }
        });
    }

    public void initObservers() {
        ticketViewModel.getInProgressTickets().observe(getViewLifecycleOwner(), ticket -> {
            inProgressAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        inProgressAdapter = new InProgressAdapter(new TicketDiffer(), ticket -> {
            this.ticket = ticket;
            Intent intent = new Intent(requireActivity(), TicketDetailsActivity.class);
            intent.putExtra(TicketDetailsActivity.DETAILS_KEY, ticket);
            ticketActivityResultLauncher.launch(intent);

//            Intent intent = new Intent(requireActivity(), TicketDetailsActivity.class);
//            intent.putExtra(DETAILS_KEY, new TicketParcelable(ticket.getTitle(), ticket.getDescription(),
//                    ticket.getTicketType(), ticket.getTicketPriority(), ticket.getNumberOfDays(), ticket.getId(), ticket.getTicketState()));
//            startActivity(intent);
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.moveBackwardTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(inProgressAdapter);
    }

    private ActivityResultLauncher<Intent> ticketActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data.getExtras() != null) {
                        Ticket ticket = data.getExtras().getParcelable(RETURN_TO_MAIN);
                        System.out.println(ticket.toString());
                        edit(ticket);
                    }
                }
            }
    );

    @SuppressLint("NotifyDataSetChanged")
    public void edit(Ticket newTicket) {
//      ticket.setTitle(newTicket.getTitle());
        ticketViewModel.editInProgressTicket(ticket, newTicket);
        inProgressAdapter.notifyDataSetChanged();

    }
}

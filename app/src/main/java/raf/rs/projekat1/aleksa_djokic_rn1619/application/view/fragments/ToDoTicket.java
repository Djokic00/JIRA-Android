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
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.adapter.ToDoAdapter;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.recycler.differ.TicketDiffer;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;
import static android.app.Activity.RESULT_OK;
import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.TicketDetailsActivity.RETURN_TO_MAIN;


public class ToDoTicket extends Fragment {
    private TicketViewModel ticketViewModel;
    private ToDoAdapter toDoAdapter;
    private RecyclerView recyclerView;
    private EditText search;
    private Ticket ticket;

    public ToDoTicket() {
        super(R.layout.fragment_todo_recycler);
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
        recyclerView = view.findViewById(R.id.todoRv);
        search = view.findViewById(R.id.searchEditText);
    }

    public void initListeners() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                ticketViewModel.filterToDoTickets(editable.toString());
            }
        });
    }

    public void initObservers() {
        ticketViewModel.getToDoTickets().observe(getViewLifecycleOwner(), ticket -> {
            System.out.println("Observers are notified");
            toDoAdapter.submitList(ticket);
        });
    }

    public void initRecycler() {
        toDoAdapter = new ToDoAdapter(new TicketDiffer(), ticket -> {
            this.ticket = ticket;
            Intent intent = new Intent(requireActivity(), TicketDetailsActivity.class);
            intent.putExtra(TicketDetailsActivity.DETAILS_KEY, ticket);
            ticketActivityResultLauncher.launch(intent);
//
//            intent.putExtra(TicketDetailsActivity.DETAILS_KEY, new TicketParcelable(ticket.getTitle(), ticket.getDescription(),
//            ticket.getTicketType(), ticket.getTicketPriority(), ticket.getNumberOfDays(), ticket.getId(), ticket.getTicketState()));
//            startActivity(intent);
        }, ticket -> {
            ticketViewModel.moveForwardTicket(ticket);
        }, ticket -> {
            ticketViewModel.removeTicket(ticket);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(toDoAdapter);
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
        ticketViewModel.editToDoTicket(ticket, newTicket);
        toDoAdapter.notifyDataSetChanged();

    }


}

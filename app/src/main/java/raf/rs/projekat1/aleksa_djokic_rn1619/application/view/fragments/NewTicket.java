package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

public class NewTicket extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextView heading;
    private Spinner ticketSpinner;
    private Spinner prioritySpinner;
    private EditText est;
    private EditText ticketTitle;
    private EditText ticketDescription;
    private Button addTicketBtn;
    private int estimation;
    private int ticketNumber = 0;
    private TicketViewModel ticketViewModel;

    public NewTicket() {
        super(R.layout.fragment_new_ticket);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        initListeners();
    }

    private void initView(View view) {
        heading = view.findViewById(R.id.newTicketHeading);
        ticketSpinner = view.findViewById(R.id.titleSpinner);
        prioritySpinner = view.findViewById(R.id.prioritySpinner);
        est = view.findViewById(R.id.estEditText);
        ticketTitle = view.findViewById(R.id.ticketTitleEditText);
        ticketDescription = view.findViewById(R.id.titleDesEditText);
        addTicketBtn = view.findViewById(R.id.addNewTicketBtn);
    }

    private void initListeners() {
        ticketSpinner.setOnItemSelectedListener(this);
        prioritySpinner.setOnItemSelectedListener(this);
        addTicketBtn.setOnClickListener(view -> {

            if (ticketSpinner.getSelectedItem().toString().equals("Ticket type") || prioritySpinner.getSelectedItem().toString().equals("Priority") ||
                    est.getText().toString().isEmpty() || ticketTitle.getText().toString().isEmpty() || ticketDescription.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Fill in all the fields", Toast.LENGTH_SHORT).show();
            }
            else if (!est.getText().toString().isEmpty()) {
                try {
                    estimation = Integer.parseInt(est.getText().toString());
                    Ticket ticket = new Ticket(ticketTitle.getText().toString(), ticketDescription.getText().toString(), ticketSpinner.getSelectedItem().toString(),
                            prioritySpinner.getSelectedItem().toString(), estimation, ticketNumber, "toDo");
                    ticketViewModel.addTicket(ticket);
                    ticketNumber++;
                    est.getText().clear();
                    ticketTitle.getText().clear();
                    ticketDescription.getText().clear();

                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Estimation has to be an integer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

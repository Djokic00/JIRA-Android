package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

public class EditTicketActivity extends AppCompatActivity {

    public static final String EDIT_KEY = "editKey";
    private TextView heading;
    private Spinner ticketSpinner;
    private Spinner prioritySpinner;
    private EditText est;
    private EditText ticketTitle;
    private EditText ticketDescription;
    private Button saveTicketBtn;
    private int estimation;
    private int ticketNumber = 0;
    private Ticket oldTicket;
    private TicketViewModel ticketViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        initView();
        parseIntent();
        initListeners();
    }

    private void initView() {
        heading = findViewById(R.id.newTicketHeading);
        ticketSpinner = findViewById(R.id.titleSpinner);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        est = findViewById(R.id.estEditText);
        ticketTitle = findViewById(R.id.ticketTitleEditText);
        ticketDescription = findViewById(R.id.titleDesEditText);
        saveTicketBtn = findViewById(R.id.saveTicketBtn);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            oldTicket = intent.getExtras().getParcelable(EDIT_KEY);
            if (oldTicket != null) {
                setSpinner(oldTicket);
                ticketTitle.setText(oldTicket.getTitle());
                est.setText(String.valueOf(oldTicket.getNumberOfDays()));
                ticketDescription.setText(oldTicket.getDescription());
            }
        }
    }

    public void setSpinner(Ticket ticket) {
        if (ticket.getTicketType().equals("Bug")) ticketSpinner.setSelection(1);
        else ticketSpinner.setSelection(2);
        switch (ticket.getTicketPriority()) {
            case "Highest":
                prioritySpinner.setSelection(1);
                break;
            case "High":
                prioritySpinner.setSelection(2);
                break;
            case "Medium":
                prioritySpinner.setSelection(3);
                break;
            case "Low":
                prioritySpinner.setSelection(4);
                break;
            case "Lowest":
                prioritySpinner.setSelection(5);
                break;
        }

    }

    private void initListeners() {
        saveTicketBtn.setOnClickListener(view -> {
            if (ticketSpinner.getSelectedItem().toString().equals("Ticket type") || prioritySpinner.getSelectedItem().toString().equals("Priority") ||
                    est.getText().toString().isEmpty() || ticketTitle.getText().toString().isEmpty() || ticketDescription.getText().toString().isEmpty()) {
                Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            }
            else if (!est.getText().toString().isEmpty()) {
                try {
                    estimation = Integer.parseInt(est.getText().toString());
                    Ticket newTicket = new Ticket(ticketTitle.getText().toString(), ticketDescription.getText().toString(), ticketSpinner.getSelectedItem().toString(),
                            prioritySpinner.getSelectedItem().toString(), estimation, ticketNumber, "toDo");
                    if (oldTicket.getTicketState().equals("toDo")) ticketViewModel.editToDoTicket(oldTicket, newTicket);
                    est.getText().clear();
                    ticketTitle.getText().clear();
                    ticketDescription.getText().clear();

                } catch (Exception e) {
                    Toast.makeText(this, "Estimation has to be an integer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
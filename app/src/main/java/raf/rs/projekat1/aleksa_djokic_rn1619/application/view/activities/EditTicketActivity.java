package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.application.R;

public class EditTicketActivity extends AppCompatActivity {

    private TextView heading;
    private Spinner ticketSpinner;
    private Spinner prioritySpinner;
    private EditText est;
    private EditText ticketTitle;
    private EditText ticketDescription;
    private Button saveTicketBtn;
    private int estimation;
    private int ticketNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);
        initView();
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

    private void initListeners() {
        saveTicketBtn.setOnClickListener(view -> {

        });
    }
}
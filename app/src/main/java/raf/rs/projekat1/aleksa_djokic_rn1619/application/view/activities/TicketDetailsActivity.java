package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;

public class TicketDetailsActivity extends AppCompatActivity {

    public static final String DETAILS_KEY = "detailsKey";
    private ImageView ticketTypeImage;
    private TextView ticketTitle;
    private TextView priorityInfo;
    private TextView estimationInfo;
    private TextView loggedTime;
    private TextView ticketDescriptionInfo;
    private Button editTicketBtn;
    private int loggedTimeCounter = 0;
    private Ticket ticket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        initView();
        initListeners();
        parseIntent();
    }

    public void initView() {
        ticketTypeImage = findViewById(R.id.typePicture);
        ticketTitle = findViewById(R.id.ticketInfo);
        priorityInfo = findViewById(R.id.priorityInfo);
        estimationInfo = findViewById(R.id.estimationInfo);
        loggedTime = findViewById(R.id.loggedInfo);
        ticketDescriptionInfo = findViewById(R.id.descriptionInfo);
        editTicketBtn = findViewById(R.id.editBtn);
    }

    public void parseIntent() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            ticket = intent.getExtras().getParcelable(DETAILS_KEY);
            if (ticket != null) {
                System.out.println(ticket.getTitle());
                if (ticket.getTicketType().equals("Bug")) ticketTypeImage.setImageResource(R.drawable.bug_icon);
                else ticketTypeImage.setImageResource(R.drawable.enhancement_icon);
                ticketTitle.setText(ticket.getTitle());
                priorityInfo.setText(ticket.getTicketPriority());
                estimationInfo.setText(String.valueOf(ticket.getNumberOfDays()));
                loggedTime.setText(String.valueOf(loggedTimeCounter));
                ticketDescriptionInfo.setText(ticket.getDescription());
            }
        }
//        if (!admin || (!ticket.getTicketState().equals("toDo") && !ticket.getTicketState().equals("inProgress"))) {
//            editTicketBtn.setVisibility(View.GONE);
//        }

    }

    public void initListeners() {
        loggedTime.setOnClickListener(view -> {
            loggedTimeCounter++;
            loggedTime.setText(String.valueOf(loggedTimeCounter));
        });

        loggedTime.setOnLongClickListener(view -> {
            loggedTimeCounter--;
            loggedTime.setText(String.valueOf(loggedTimeCounter));
            return false;
        });
    }
}
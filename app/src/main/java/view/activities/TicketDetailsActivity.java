package view.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.application.R;
import models.Ticket;
import models.TicketParcelable;

public class TicketDetailsActivity extends AppCompatActivity {

    public static final String DETAILS_KEY = "detailsKey";
    private ImageView ticketTypeImage;
    private TextView ticketTitle;
    private TextView priorityInfo;
    private TextView estimationInfo;
    private TextView loggedTime;
    private TextView ticketDescriptionInfo;
    private Button editTicketBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        initView();
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
            Ticket ticket = intent.getExtras().getParcelable(DETAILS_KEY);
            if (ticket != null) {
                System.out.println(ticket.getTitle());
                if (ticket.getTicketType().equals("Bug")) ticketTypeImage.setImageResource(R.drawable.bug_icon);
                else ticketTypeImage.setImageResource(R.drawable.enhancement_icon);
                ticketTitle.setText(ticket.getTitle());
                priorityInfo.setText(ticket.getTicketPriority());
                estimationInfo.setText(String.valueOf(ticket.getNumberOfDays()));
                loggedTime.setText("0");
                ticketDescriptionInfo.setText(ticket.getDescription());
            }
        }

    }

    public void initListeners() {

    }
}
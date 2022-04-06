package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.TicketParcelable;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments.ToDoTicket;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.EditTicketActivity.EDIT_KEY;
import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.EditTicketActivity.RETURN_TO_DETAILS;
import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.PACKAGE_NAME;

public class TicketDetailsActivity extends AppCompatActivity {

    public static final String DETAILS_KEY = "detailsKey";
    public static final String RETURN_TO_MAIN = "lastChance";
    private ImageView ticketTypeImage;
    private TextView ticketTitle;
    private TextView priorityInfo;
    private TextView estimationInfo;
    private TextView loggedTime;
    private TextView ticketDescriptionInfo;
    private Button editTicketBtn;
    private int loggedTimeCounter = 0;
    private Ticket ticket;
    private TicketViewModel ticketViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
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
//            ticketViewModel.editTicket(ticket);
            if (ticket != null) {
                if (ticket.getTicketType().equals("Bug")) ticketTypeImage.setImageResource(R.drawable.bug_icon);
                else ticketTypeImage.setImageResource(R.drawable.enhancement_icon);
                ticketTitle.setText(ticket.getTitle());
                priorityInfo.setText(ticket.getTicketPriority());
                estimationInfo.setText(String.valueOf(ticket.getNumberOfDays()));
                loggedTime.setText(String.valueOf(loggedTimeCounter));
                ticketDescriptionInfo.setText(ticket.getDescription());
            }
        }
        SharedPreferences sharedPreferences = getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        String admin = sharedPreferences.getString(LoginActivity.CREDENTIAL_KEY_IS_ADMIN, null);
        if (!admin.equals("true") || (ticket.getTicketState().equals("done"))) {
            editTicketBtn.setVisibility(View.GONE);
        }
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

        editTicketBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, EditTicketActivity.class);
            intent.putExtra(EDIT_KEY, ticket);
            editActivityResultLauncher.launch(intent);
        });
    }

    private ActivityResultLauncher<Intent> editActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data.getExtras() != null) {
                        Ticket ticket = data.getExtras().getParcelable(RETURN_TO_DETAILS);
                        System.out.println(ticket.toString());
                        sendBackToMain(ticket);
                    }
                }
            }
    );

    public void sendBackToMain(Ticket newTicket) {
        Intent returnIntent = new Intent(this, ToDoTicket.class);
        returnIntent.putExtra(RETURN_TO_MAIN, newTicket);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        // kad se odradi finish, launcher koji ga je startovao dobija odgovor
    }
}
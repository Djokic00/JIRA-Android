package raf.rs.projekat1.aleksa_djokic_rn1619.application.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.application.R;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels.TicketViewModel;

public class Statistics extends Fragment {
    private TextView header;
    private TextView toDo;
    private TextView inProgress;
    private TextView done;
    private TextView toDoEnhancement;
    private TextView toDoBug;
    private TextView inProgressEnhancement;
    private TextView inProgressBug;
    private TextView doneEnhancement;
    private TextView doneBug;
    private TicketViewModel ticketViewModel;

    public Statistics() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        setValues();
        initObservers();
    }

    private void initView(View view) {
        header = view.findViewById(R.id.rafStats);
        toDo = view.findViewById(R.id.number1);
        inProgress = view.findViewById(R.id.number4);
        done = view.findViewById(R.id.number7);
        toDoEnhancement = view.findViewById(R.id.number2);
        toDoBug = view.findViewById(R.id.number3);
        inProgressEnhancement = view.findViewById(R.id.number5);
        inProgressBug = view.findViewById(R.id.number6);
        doneEnhancement = view.findViewById(R.id.number8);
        doneBug = view.findViewById(R.id.number9);

    }

    private void setValues() {
        Integer numberOfToDo = ticketViewModel.getNumberOfToDo();
        Integer numberOfInProgress = ticketViewModel.getNumberOfInProgress();
        Integer numberOfDone = ticketViewModel.getNumberOfDone();

        toDo.setText(String.valueOf(numberOfToDo));
        toDoBug.setText(String.valueOf(ticketViewModel.getNumberOfBugsToDo()));
        toDoEnhancement.setText(String.valueOf(ticketViewModel.getNumberOfEnhancementToDo()));
        inProgress.setText(String.valueOf(numberOfInProgress));
        inProgressBug.setText(String.valueOf(ticketViewModel.getNumberOfBugsInProgress()));
        inProgressEnhancement.setText(String.valueOf(ticketViewModel.getNumberOfEnhancementInProgress()));
        done.setText(String.valueOf(numberOfDone));
        doneBug.setText(String.valueOf(ticketViewModel.getNumberOfBugsDone()));
        doneEnhancement.setText(String.valueOf(ticketViewModel.getNumberOfEnhancementDone()));
    }

    public void initObservers() {
        ticketViewModel.getToDoTickets().observe(getViewLifecycleOwner(), ticket -> {
            setValues();
        });

        ticketViewModel.getInProgressTickets().observe(getViewLifecycleOwner(), ticket -> {
            setValues();
        });

        ticketViewModel.getDoneTickets().observe(getViewLifecycleOwner(), ticket -> {
            setValues();
        });
    }
}

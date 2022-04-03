package view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.application.R;
import view.activities.LoginActivity;
import viewmodels.TicketViewModel;

public class Statistics extends Fragment {
    private TextView header;
    private TextView toDo;
    private TextView inProgress;
    private TextView done;
    private TicketViewModel ticketViewModel;

    public Statistics() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticketViewModel = new ViewModelProvider(requireActivity()).get(TicketViewModel.class);
        initView(view);
        initListeners();
        initObservers();
    }

    private void initView(View view) {
        header = view.findViewById(R.id.rafStats);
        toDo = view.findViewById(R.id.toDoInfo);
        inProgress = view.findViewById(R.id.inProgressInfo);
        done = view.findViewById(R.id.doneInfo);
    }

    private void initListeners() {
        Integer numberOfToDo = ticketViewModel.getNumberOfToDo();
        Integer numberOfInProgress = ticketViewModel.getNumberOfInProgress();
        Integer numberOfDone = ticketViewModel.getNumberOfDone();

        toDo.setText(String.valueOf(numberOfToDo));
        inProgress.setText(String.valueOf(numberOfInProgress));
        done.setText(String.valueOf(numberOfDone));
    }

    public void initObservers() {
        ticketViewModel.getToDoTickets().observe(getViewLifecycleOwner(), ticket -> {
            Integer numberOfToDo = ticketViewModel.getNumberOfToDo();
            toDo.setText(String.valueOf(numberOfToDo));
        });

        ticketViewModel.getInProgressTickets().observe(getViewLifecycleOwner(), ticket -> {
            Integer numberOfInProgress = ticketViewModel.getNumberOfInProgress();
            inProgress.setText(String.valueOf(numberOfInProgress));
        });

        ticketViewModel.getDoneTickets().observe(getViewLifecycleOwner(), ticket -> {
            Integer numberOfDone = ticketViewModel.getNumberOfDone();
            done.setText(String.valueOf(numberOfDone));
        });

    }
}

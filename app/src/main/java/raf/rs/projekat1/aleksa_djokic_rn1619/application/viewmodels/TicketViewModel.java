package raf.rs.projekat1.aleksa_djokic_rn1619.application.viewmodels;

import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import raf.rs.projekat1.aleksa_djokic_rn1619.application.models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketViewModel extends ViewModel {
    public static int counter = 15;
    private final MutableLiveData<List<Ticket>> toDoTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneTickets = new MutableLiveData<>();
    private final ArrayList<Ticket> toDoTicketList = new ArrayList<>();
    private final ArrayList<Ticket> inProgressTicketList = new ArrayList<>();
    private final ArrayList<Ticket> doneTicketList = new ArrayList<>();// simulacija baze podatala

    public TicketViewModel() {
        for(int i = 0; i < counter; i++) {
            Ticket ticket = new Ticket("Ticket " + i, "Laptop bug","Bug", "High", 5, i, "toDo");
            toDoTicketList.add(ticket);
        }
        for(int i = counter; i < counter + 10; i++) {
            Ticket ticket = new Ticket("Ticket " + i, "Mobile enhancement","Enhancement", "Low", 10, i, "inProgress");
            inProgressTicketList.add(ticket);
        }
        ArrayList<Ticket> toDoListToSubmit = new ArrayList<>(toDoTicketList);
        ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
        toDoTickets.setValue(toDoListToSubmit);
        inProgressTickets.setValue(inProgressListToSubmit);
    }

    public void addTicket(Ticket ticket) {
        if (ticket.getTicketState().equals("toDo")) {
            toDoTicketList.add(ticket);
            ArrayList<Ticket> toDoListToSubmit = new ArrayList<>(toDoTicketList);
            toDoTickets.setValue(toDoListToSubmit);
        }
        else if (ticket.getTicketState().equals("inProgress")) {
            inProgressTicketList.add(ticket);
            ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(inProgressListToSubmit);
        }
        else {
            doneTicketList.add(ticket);
            ArrayList<Ticket> doneListToSubmit = new ArrayList<>(doneTicketList);
            doneTickets.setValue(doneListToSubmit);
        }
    }

    public void removeTicket(Ticket ticket) {
        if (ticket.getTicketState().equals("toDo")) {
            toDoTicketList.remove(ticket);
            ArrayList<Ticket> toDoListToSubmit = new ArrayList<>(toDoTicketList);
            toDoTickets.setValue(toDoListToSubmit);
        }
        else if (ticket.getTicketState().equals("inProgress")) {
            inProgressTicketList.remove(ticket);
            ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(inProgressListToSubmit);
        }
    }

    public void moveForwardTicket(Ticket ticket) {
        if (ticket.getTicketState().equals("toDo")) {
            removeTicket(ticket);
            ticket.setTicketState("inProgress");
            addTicket(ticket);
        }
        else if (ticket.getTicketState().equals("inProgress")) {
            removeTicket(ticket);
            ticket.setTicketState("done");
            addTicket(ticket);
        }
    }

    public void moveBackwardTicket(Ticket ticket) {
        if (ticket.getTicketState().equals("inProgress")) {
            removeTicket(ticket);
            ticket.setTicketState("toDo");
            addTicket(ticket);
        }
    }

    public void editTicket(Ticket oldTicket) {
        int index = toDoTicketList.indexOf(oldTicket);
        if (index < 0) {
            return;
        }
        toDoTicketList.set(index, oldTicket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoTicketList);
        toDoTickets.setValue(listToSubmit);
    }

    public void editToDoTicket(Ticket oldTicket, Ticket newTicket) {
        for (Ticket ticket: toDoTicketList) {
            if (ticket.getId() == oldTicket.getId()) {
                ticket.setTitle(newTicket.getTitle());
                ticket.setDescription(newTicket.getDescription());
                ticket.setTicketType(newTicket.getTicketType());
                ticket.setTicketPriority(newTicket.getTicketPriority());
                ticket.setNumberOfDays(newTicket.getNumberOfDays());
                ticket.setTicketState(oldTicket.getTicketState());
                System.out.println(ticket);
            }
        }
        ArrayList<Ticket> listToSubmit = new ArrayList<>(toDoTicketList);
        System.out.println("Dolazi do ovde");
        toDoTickets.setValue(listToSubmit);
    }

    public void editInProgressTicket(Ticket oldTicket, Ticket newTicket) {
        for (Ticket ticket: inProgressTicketList) {
            if (ticket.getId() == oldTicket.getId()) {
                ticket.setTitle(newTicket.getTitle());
                ticket.setDescription(newTicket.getDescription());
                ticket.setTicketType(newTicket.getTicketType());
                ticket.setTicketPriority(newTicket.getTicketPriority());
                ticket.setNumberOfDays(newTicket.getNumberOfDays());
                ticket.setTicketState(oldTicket.getTicketState());
            }
        }
        ArrayList<Ticket> listToSubmit = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(listToSubmit);
    }


    public void filterToDoTickets(String filter) {
        List<Ticket> filteredList = toDoTicketList.stream().filter(ticket ->
                ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        toDoTickets.setValue(filteredList);
    }

    public void filterInProgressTickets(String filter) {
        List<Ticket> filteredList = inProgressTicketList.stream().filter(ticket ->
                ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        inProgressTickets.setValue(filteredList);
    }

    public void filterDoneTickets(String filter) {
        List<Ticket> filteredList = doneTicketList.stream().filter(ticket ->
                ticket.getTitle().toLowerCase().startsWith(filter.toLowerCase())).collect(Collectors.toList());
        doneTickets.setValue(filteredList);
    }

    public long getNumberOfBugsToDo() {
        return toDoTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Bug")).count();
    }

    public long getNumberOfEnhancementToDo() {
        return toDoTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Enhancement")).count();
    }

    public long getNumberOfBugsInProgress() {
        return inProgressTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Bug")).count();
    }

    public long getNumberOfEnhancementInProgress() {
        return inProgressTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Enhancement")).count();
    }

    public long getNumberOfBugsDone() {
        return doneTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Bug")).count();
    }

    public long getNumberOfEnhancementDone() {
        return doneTicketList.stream().filter(ticket -> ticket.getTicketType().equals("Enhancement")).count();
    }

    public int getNumberOfToDo() {
        return toDoTicketList.size();
    }

    public int getNumberOfInProgress() {
        return inProgressTicketList.size();
    }

    public int getNumberOfDone() {
        return doneTicketList.size();
    }

    public MutableLiveData<List<Ticket>> getToDoTickets() {
        return toDoTickets;
    }

    public MutableLiveData<List<Ticket>> getInProgressTickets() { return inProgressTickets; }

    public MutableLiveData<List<Ticket>> getDoneTickets() {
        return doneTickets;
    }

}

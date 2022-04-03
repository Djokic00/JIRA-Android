package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import models.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketViewModel extends ViewModel {
    public static int counter = 10;
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
            ticket.setTicketState("inProgress");
            toDoTicketList.remove(ticket);
            inProgressTicketList.add(ticket);
            ArrayList<Ticket> toDoListToSubmit = new ArrayList<>(toDoTicketList);
            toDoTickets.setValue(toDoListToSubmit);
            ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(inProgressListToSubmit);
        }
        else if (ticket.getTicketState().equals("inProgress")) {
            ticket.setTicketState("done");
            inProgressTicketList.remove(ticket);
            doneTicketList.add(ticket);
            ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(inProgressListToSubmit);
            ArrayList<Ticket> doneListToSubmit = new ArrayList<>(doneTicketList);
            doneTickets.setValue(doneListToSubmit);
        }
    }

    public void moveBackwardTicket(Ticket ticket) {
        if (ticket.getTicketState().equals("inProgress")) {
            ticket.setTicketState("toDo");
            inProgressTicketList.remove(ticket);
            toDoTicketList.add(ticket);
            ArrayList<Ticket> inProgressListToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(inProgressListToSubmit);
            ArrayList<Ticket> toDoListToSubmit = new ArrayList<>(toDoTicketList);
            toDoTickets.setValue(toDoListToSubmit);
        }
    }


    public void editTicket(Ticket oldTicket, Ticket newTicket) {
//        for (Ticket p: ticketList){
//            if(p.getId() == old.getId()) {
//                p.setKolicina(newPrihod.getKolicina());
//                p.setNaslov(newPrihod.getNaslov());
//                if(newPrihod.getFile() != null) {
//                    p.setFile(newPrihod.getFile());
//                }else {
//                    p.setOpis(newPrihod.getOpis());
//                }
//            }

        }
//        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
//        prihodi.setValue(listToSubmit);
    public void filterTickets(String filter) {

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

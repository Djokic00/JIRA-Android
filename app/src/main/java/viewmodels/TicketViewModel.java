package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketViewModel extends ViewModel {
    public static int counter = 10;
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private final ArrayList<Ticket> ticketList = new ArrayList<>(); // simulacija baze podatala

    public TicketViewModel() {
        for(int i = 0; i <= counter; i++) {
            Ticket ticket = new Ticket("Ticket " + i, "Laptop bug","Bug", "High", 5, i);
            ticketList.add(ticket);
        }
    }

    public void addTicket(Ticket ticket) {
        ticketList.add(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>();
        tickets.setValue(listToSubmit);
    }

    public void removeTicket(Ticket ticket) {
        ticketList.remove(ticket);
        ArrayList<Ticket> listToSubmit = new ArrayList<>();
        tickets.setValue(listToSubmit);
    }

//    private int getNumberOfTickets() {
//        return ticket
//    }
    public void editTicket(Ticket old, Ticket newPrihod) {
        for (Ticket p: ticketList){
            if(p.getId() == old.getId()) {
//                p.setKolicina(newPrihod.getKolicina());
//                p.setNaslov(newPrihod.getNaslov());
//                if(newPrihod.getFile() != null) {
//                    p.setFile(newPrihod.getFile());
//                }else {
//                    p.setOpis(newPrihod.getOpis());
//                }
            }

        }
//        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiLista);
//        prihodi.setValue(listToSubmit);
}


    public MutableLiveData<List<Ticket>> getTickets() {
        return tickets;
    }
}

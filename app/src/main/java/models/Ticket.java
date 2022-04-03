package models;

import android.widget.ImageView;

public class Ticket {
    private String title;
    private String description;
    private String ticketType;
    private String ticketPriority;
    private String ticketState;
    private int id;
    private int numberOfDays;

    public Ticket(String title, String description, String ticketType, String ticketPriority, int numberOfDays, int id, String ticketState) {
        this.title = title;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketPriority = ticketPriority;
        this.numberOfDays = numberOfDays;
        this.id = id;
        this.ticketState = ticketState;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(String ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }
}

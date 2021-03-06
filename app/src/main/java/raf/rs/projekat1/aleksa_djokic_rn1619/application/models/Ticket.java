package raf.rs.projekat1.aleksa_djokic_rn1619.application.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {
    protected String title;
    protected String description;
    protected String ticketType;
    protected String ticketPriority;
    protected String ticketState;
    protected int id;
    protected int numberOfDays;

    public Ticket(String title, String description, String ticketType, String ticketPriority, int numberOfDays, int id, String ticketState) {
        this.title = title;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketPriority = ticketPriority;
        this.numberOfDays = numberOfDays;
        this.id = id;
        this.ticketState = ticketState;
    }

    protected Ticket(Parcel in) {
        title = in.readString();
        description = in.readString();
        ticketType = in.readString();
        ticketPriority = in.readString();
        numberOfDays = in.readInt();
        id = in.readInt();
        ticketState = in.readString();
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(ticketType);
        parcel.writeString(ticketPriority);
        parcel.writeInt(numberOfDays);
        parcel.writeInt(id);
        parcel.writeString(ticketState);
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", ticketPriority='" + ticketPriority + '\'' +
                ", ticketState='" + ticketState + '\'' +
                ", id=" + id +
                ", numberOfDays=" + numberOfDays +
                '}';
    }



}

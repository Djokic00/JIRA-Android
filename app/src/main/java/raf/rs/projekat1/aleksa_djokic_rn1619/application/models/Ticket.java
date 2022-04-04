package raf.rs.projekat1.aleksa_djokic_rn1619.application.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

import static raf.rs.projekat1.aleksa_djokic_rn1619.application.view.activities.LoginActivity.CREDENTIAL_KEY_IS_ADMIN;

public class Ticket { // implements Parcelable {
    protected String title;
    protected String description;
    protected String ticketType;
    protected String ticketPriority;
    protected String ticketState;
    protected int id;
    protected int numberOfDays;
    private Context context;
    private SharedPreferences sharedPrefs;

    public Ticket(String title, String description, String ticketType, String ticketPriority, int numberOfDays, int id, String ticketState) {
        this.title = title;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketPriority = ticketPriority;
        this.numberOfDays = numberOfDays;
        this.id = id;
        this.ticketState = ticketState;
    }

    public Ticket(String title, String description, String ticketType, String ticketPriority, int numberOfDays, String ticketState) {
        this.title = title;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketPriority = ticketPriority;
        this.numberOfDays = numberOfDays;
        this.ticketState = ticketState;
    }

    public Ticket(Context context) {
        this.context = context;
        sharedPrefs = context.getSharedPreferences("name", 0);
    }

    protected Ticket(Parcel in) {
        title = in.readString();
        description = in.readString();
        ticketType = in.readString();
        ticketPriority = in.readString();
        ticketState = in.readString();
        id = in.readInt();
        numberOfDays = in.readInt();
    }

//    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
//        @Override
//        public Ticket createFromParcel(Parcel in) {
//            return new Ticket(in);
//        }
//
//        @Override
//        public Ticket[] newArray(int size) {
//            return new Ticket[size];
//        }
//    };

    private String doSomething(){
        return sharedPrefs.getString(CREDENTIAL_KEY_IS_ADMIN, "defValue");
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
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(title);
//        parcel.writeString(description);
//        parcel.writeString(ticketType);
//        parcel.writeString(ticketPriority);
//        parcel.writeString(ticketState);
//        parcel.writeInt(id);
//        parcel.writeInt(numberOfDays);
//    }
}

package raf.rs.projekat1.aleksa_djokic_rn1619.application.models;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketParcelable extends Ticket implements Parcelable {

    public TicketParcelable(String title, String description, String ticketType, String ticketPriority, int numberOfDays, String ticketState) {
        super(title, description, ticketType, ticketPriority, numberOfDays, ticketState);
    }

    public TicketParcelable(Parcel in) {
        super(in.readString(), in.readString(), in.readString(), in.readString(), in.readInt(), in.readString());
    }

    public static final Creator<TicketParcelable> CREATOR = new Creator<TicketParcelable>() {
        @Override
        public TicketParcelable createFromParcel(Parcel in) {
            return new TicketParcelable(in);
        }

        @Override
        public TicketParcelable[] newArray(int size) {
            return new TicketParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(ticketType);
        parcel.writeString(ticketPriority);
        parcel.writeInt(numberOfDays);
        parcel.writeString(ticketState);

    }
}
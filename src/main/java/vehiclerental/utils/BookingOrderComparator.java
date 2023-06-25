package vehiclerental.utils;

import vehiclerental.models.Booking;

import java.util.Comparator;

public class BookingOrderComparator implements Comparator<Booking> {
    @Override
    public int compare(Booking b1, Booking b2) {
        if(b1.getSlot().getStartTime().equals(b2.getSlot().getStartTime())) {
            return b1.getSlot().getEndTime().compareTo(b2.getSlot().getEndTime());
        }
        return b1.getSlot().getStartTime().compareTo(b2.getSlot().getStartTime());
    }
}

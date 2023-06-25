package vehiclerental.models;

import lombok.Getter;
import vehiclerental.utils.BookingOrderComparator;

import java.util.Set;
import java.util.TreeSet;

@Getter
public class Calender {
    private int id;
    private Set<Booking> bookings = new TreeSet<>(new BookingOrderComparator());

    public boolean isVehicleFree(Interval givenSlot) {
        for(Booking b: bookings) {
            Interval bookedInterval = b.getSlot();

            if(doesIntersect(bookedInterval, givenSlot)) {
                return false;
            }
        }
        return true;
    }

    public void removeBookingFromCalender(Booking booking) {
        bookings.remove(booking);
    }

    private boolean doesIntersect(Interval i1, Interval i2) {
        Interval first, second;
        if(i1.getStartTime() <= i2.getStartTime()) {
            first = i1;
            second = i2;
        } else {
            first = i2;
            second = i1;
        }

        if(second.getStartTime() < first.getEndTime()) {
            return true;
        }
        return false;
    }
}

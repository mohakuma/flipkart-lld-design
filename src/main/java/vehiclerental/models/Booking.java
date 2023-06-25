package vehiclerental.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vehiclerental.enums.BookingStatus;

@Getter
@Setter
@Builder
public class Booking {
    private Integer id;
    private User bookedBy;

    private Branch bookingBranch;
    private Vehicle bookedVehicle;

    private Interval slot;

    private BookingStatus status;
}

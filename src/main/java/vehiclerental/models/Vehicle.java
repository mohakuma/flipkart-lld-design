package vehiclerental.models;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.enums.CarType;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Vehicle {
    public Vehicle(
            Integer id, CarType carType, Integer price) {
//    }, Integer noOfSeats) {
        this.id = id;
        this.carType = carType;
        this.price = price;
//        this.noOfSeats = noOfSeats;
    }

    private Integer id;
    private CarType carType;
    private Integer price; // price per hour of the vehicle in Rs
//    @Builder.Default
//    private Integer noOfSeats;

    private Calender bookingCalender = new Calender();

//    private Long startTime; // booking start time
//    private Long endTime; // booking end time

//    public Boolean isBooked() {
//        return (startTime != null);
//    }
}

package vehiclerental.models;

import lombok.*;
import vehiclerental.enums.CarType;
import vehiclerental.enums.VehicleType;

@Getter
@Setter
//@AllArgsConstructor
//@Builder
//@NoArgsConstructor
public class CarVehicle extends Vehicle {
    public CarVehicle(Integer id, CarType carType, Integer price) {
//    }, Integer noOfSeats) {
        super(id, carType, price);
//        , noOfSeats);
//        this.carType = carType;
    }

//    private CarType carType;
}

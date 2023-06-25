package vehiclerental.models;

import lombok.Getter;
import lombok.Setter;
import vehiclerental.enums.CarType;

@Getter
@Setter
//@Builder
//@AllArgsConstructor
public class BikeVehicle extends Vehicle{
    public BikeVehicle(Integer id, CarType carType, Integer price) {
        super(id, carType, price);
//        , 2);
    }
}

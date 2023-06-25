package vehiclerental.strategy;

import vehiclerental.enums.CarType;
import vehiclerental.models.Vehicle;

import java.util.List;
import java.util.Map;

public interface RentingStrategy {
    void setNextStrategy(RentingStrategy strategy);

    Vehicle chooseVehicle(
            List<CarType> eligibleCarTypes, Map<CarType, List<Vehicle>> availableVehicles);
}

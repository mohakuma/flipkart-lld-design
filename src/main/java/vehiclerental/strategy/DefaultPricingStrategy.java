package vehiclerental.strategy;

import vehiclerental.enums.CarType;
import vehiclerental.models.Vehicle;

import java.util.List;
import java.util.Map;

public class DefaultPricingStrategy implements RentingStrategy {
    private RentingStrategy nextStrategy;

    @Override
    public void setNextStrategy(RentingStrategy nextStrategy) {
        this.nextStrategy = nextStrategy;
    }

    @Override
    public Vehicle chooseVehicle(
            List<CarType> eligibleCarTypes, Map<CarType, List<Vehicle>> availableVehicles) {
        System.out.println("current strategy is default strategy");

        Vehicle selectedVehicle;
        if(eligibleCarTypes.size() == 0) {
            System.out.println("No car types is eligible based on number of seats");
            return null;
        }
        // if only 1 carType is eligible
        else if(eligibleCarTypes.size() == 1) {
            System.out.println("selected vehicle type is: " + eligibleCarTypes.get(0).toString());
            selectedVehicle = availableVehicles.get(eligibleCarTypes.get(0)).get(0);
        }
        // if more than 1 carType is eligible
        else {
            return nextStrategy.chooseVehicle(eligibleCarTypes, availableVehicles);
        }
        return selectedVehicle;
    }
}

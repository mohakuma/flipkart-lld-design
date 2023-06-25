package vehiclerental.strategy;

import vehiclerental.enums.CarType;
import vehiclerental.models.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BestVehicleTypeStrategy implements RentingStrategy {

    private RentingStrategy nextStrategy;

    @Override
    public void setNextStrategy(RentingStrategy nextStrategy) {
        this.nextStrategy = nextStrategy;
    }

    @Override
    public Vehicle chooseVehicle(
            List<CarType> eligibleCarTypes, Map<CarType, List<Vehicle>> availableVehicles) {
        System.out.println("current strategy is best vehicle type strategy");

        Set<CarType> carTypesInPriorityOrder = CarType.getCarTypesSetOrderedByPriority(eligibleCarTypes);

        CarType firstPriorityCarType = carTypesInPriorityOrder.iterator().next();

        System.out.println("selected car type is: " + firstPriorityCarType.toString());
        Vehicle selectedVehicle = availableVehicles.get(firstPriorityCarType).get(0);

        return selectedVehicle;
    }
}

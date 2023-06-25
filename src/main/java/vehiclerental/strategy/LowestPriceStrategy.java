package vehiclerental.strategy;

import vehiclerental.enums.CarType;
import vehiclerental.models.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LowestPriceStrategy implements  RentingStrategy {

    private RentingStrategy nextStrategy;

    @Override
    public void setNextStrategy(RentingStrategy nextStrategy) {
        this.nextStrategy = nextStrategy;
    }

    @Override
    public Vehicle chooseVehicle(
            List<CarType> eligibleCarTypes, Map<CarType, List<Vehicle>> availableVehicles) {
        System.out.println("current strategy is lowest price strategy");
        Vehicle selectedVehicle;
//        if(noOfSeats == 7) {
//            if(!availableVehicles.containsKey(CarType.SUV)) {
//                System.out.println("No vehicle is available as of now");
//                throw new NoVehicleAvailableException();
//            }
//            vehicle = availableVehicles.get(CarType.SUV).get(0);
//        } else if(noOfSeats == 2) {
//            if(!availableVehicles.containsKey(CarType.BIKE)) {
//                System.out.println("No vehicle is available as of now");
//                throw new NoVehicleAvailableException();
//            }
//            vehicle = availableVehicles.get(CarType.BIKE).get(0);
//        } else {
//            List<Vehicle> hatchbacks  = availableVehicles.get(CarType.HATCHBACK);
//            List<Vehicle> sedans = availableVehicles.get(CarType.SEDAN);
//
//            if(hatchbacks == null && sedans == null) {
//                System.out.println("No vehicle is available as of now");
//                throw new NoVehicleAvailableException();
//            }
//
//            if(hatchbacks == null && sedans != null) {
//                vehicle = sedans.get(0);
//            } else if(sedans == null && hatchbacks != null) {
//                vehicle = hatchbacks.get(0);
//            } else {
//
//                int price1 = availableVehicles.get(CarType.HATCHBACK).get(0).getPrice();
//                int price2 = availableVehicles.get(CarType.SEDAN).get(0).getPrice();
//
//                if (price1 < price2) {
//                    vehicle = availableVehicles.get(CarType.HATCHBACK).get(0);
//                } else if (price2 < price1) {
//                    vehicle = availableVehicles.get(CarType.SEDAN).get(0);
//                } else {
//                    vehicle = availableVehicles.get(CarType.SEDAN).get(0);
//                }
//            }
//        }
//        vehicle.setStartTime(startTime);
//        vehicle.setEndTime(endTime);

        Map<Integer, List<CarType>> carTypeToPriceMap = new TreeMap<>();
        eligibleCarTypes.stream()
//                .filter(carType -> availableVehicles.containsKey(carType))
                .forEach(carType -> {
                    int price = availableVehicles.get(carType).get(0).getPrice();
                    if(carTypeToPriceMap.containsKey(price)) {
                        carTypeToPriceMap.get(price).add(carType);
                    } else {
                        List<CarType> carTypes = carTypeToPriceMap.getOrDefault(price, new ArrayList<>());
                        carTypes.add(carType);
                        carTypeToPriceMap.put(price, carTypes);
                    }
                });

//        if(carTypeToPriceMap.isEmpty()) {
//            System.out.println("No Vehicle is available at the moment");
//            return null;
//        }

        Map.Entry<Integer, List<CarType>> firstKeyValue = carTypeToPriceMap.entrySet().iterator().next();
        // only 1 vehicle type with lowest price
        if(firstKeyValue.getValue().size() == 1) {
            System.out.println("selected vehicle type is " + firstKeyValue.getValue().get(0).toString());
            selectedVehicle = availableVehicles.get(firstKeyValue.getValue().get(0)).get(0);
        } else {
            return nextStrategy.chooseVehicle(firstKeyValue.getValue(), availableVehicles);
        }

        return selectedVehicle;
    }
}

package vehiclerental.service;

import lombok.NonNull;
import vehiclerental.enums.CarType;
import vehiclerental.exceptions.BranchNotExistException;
import vehiclerental.models.Address;
import vehiclerental.models.Branch;
import vehiclerental.models.Interval;
import vehiclerental.models.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BranchService {
    private final List<Branch> branches;
//    private final Map<String, List<Branch>> cityToBranchMap;

    public BranchService() {
        branches = new ArrayList<>();
//        cityToBranchMap = new HashMap<>();
    }

    public Branch addBranch(
            @NonNull String city, @NonNull String name, List<Vehicle> vehicles) {
        Branch branchToAdd = Branch.builder()
                .id(branches.size() + 1)
                .address(Address.builder()
                        .city(city)
                        .build())
                .name(name)
//                .vehicleList(vehicles)
                .build();

        int noOfVehicles = 0;
        if(vehicles != null && !vehicles.isEmpty()) {
            branchToAdd.setVehicleList(vehicles);
            noOfVehicles = vehicles.size();
        }

        branches.add(branchToAdd);
        System.out.println("added new branch: " + name + " to city: " + city +
                " with no of vehicles: " + noOfVehicles);
        return branchToAdd;
    }

    public Branch getBranchByName(@NonNull String name) {
        Optional<Branch> branch =  branches.stream()
                .filter(b -> name.equals(b.getName()))
                .findFirst();

        if(!branch.isPresent()) {
            System.out.println("no branch exists with name: " + name);
            throw new BranchNotExistException();
        }
        return branch.get();
    }

    public void addVehiclesToBranch(
            @NonNull String branchName, @NonNull List<Vehicle> vehicles) {
        Branch branch = getBranchByName(branchName);
        branch.getVehicleList().addAll(vehicles);
    }

    public void addNewVehicleToBranch(
            @NonNull String branchName, @NonNull CarType carType, Integer price) {
        Branch branch = getBranchByName(branchName);
        List<Vehicle> allVehicles = branch.getVehicleList();

        Optional<Vehicle> existingVehicle = allVehicles.stream()
                .filter(vehicle -> vehicle.getCarType().equals(carType))
                .findFirst();

        Vehicle newVehicle = new Vehicle(allVehicles.size() + 1, carType, null);

        if(existingVehicle.isPresent()) {
            newVehicle.setPrice(existingVehicle.get().getPrice());
        } else {
            newVehicle.setPrice(price);
        }
        allVehicles.add(newVehicle);
    }

    public void addVehiclesToBranch(
            @NonNull String branchName, @NonNull CarType carType, int quantity, int price) {
        Branch branch = getBranchByName(branchName);
        List<Vehicle> allVehicles = branch.getVehicleList();

        while(quantity > 0) {
            Vehicle newVehicle = new Vehicle(allVehicles.size() + 1, carType, price);
            allVehicles.add(newVehicle);
            quantity--;
        }
    }

    public Map<CarType, List<Vehicle>> getAllAvailableVehicles(
            @NonNull String branchName, @NonNull Interval givenSlot) {
        Branch branch = getBranchByName(branchName);
        List<Vehicle> allVehicles = branch.getVehicleList();

        List<Vehicle> availableVehicles =  allVehicles.stream()
                .filter(v -> v.getBookingCalender().isVehicleFree(givenSlot))
                .collect(Collectors.toList());

        Map<CarType, List<Vehicle>> result = new HashMap<>();
        for(Vehicle v: availableVehicles) {
            if(result.containsKey(v.getCarType())) {
                result.get(v.getCarType()).add(v);
            } else {
                List<Vehicle> vehicles = new ArrayList<>();
                vehicles.add(v);
                result.put(v.getCarType(), vehicles);
            }
        }
        return result;
    }

    public Set<CarType> getAllCarTypes(@NonNull String branchName) {
        Branch branch = getBranchByName(branchName);
        List<Vehicle> allVehicles = branch.getVehicleList();

        return allVehicles.stream()
                .map(vehicle -> vehicle.getCarType())
                .collect(Collectors.toSet());
    }

    public List<Branch> getBranches() {
        return this.branches;
    }
}

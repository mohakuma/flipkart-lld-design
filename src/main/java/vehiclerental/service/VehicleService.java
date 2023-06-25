package vehiclerental.service;

import lombok.NonNull;
import vehiclerental.models.Vehicle;

import java.util.List;

public class VehicleService {
    private final BranchService branchService;

    public VehicleService(BranchService branchService) {
        this.branchService = branchService;
    }

    public void addVehiclesToBranch(
            @NonNull String branchName, @NonNull List<Vehicle> vehicles) {

    }
}

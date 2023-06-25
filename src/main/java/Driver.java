import vehiclerental.enums.CarType;
import vehiclerental.models.Booking;
import vehiclerental.models.Branch;
import vehiclerental.models.User;
import vehiclerental.service.BranchService;
import vehiclerental.service.RentingService;
import vehiclerental.strategy.RentingStrategyChain;

public class Driver {
    public static void main(String[] args) {
        BranchService branchService = new BranchService();
        RentingStrategyChain rentingStrategyChain = new RentingStrategyChain();
        RentingService rentingService  = new RentingService(branchService, rentingStrategyChain);

        User user1 = User.builder()
                .name("akash")
                .email("akash@gmail.com")
                .build();
        User user2 = User.builder()
                .name("ramesh")
                .email("ramesh@gmail.com")
                .build();

        // add branch koramangala
        Branch branch1 = branchService.addBranch("bengaluru", "koramangala", null);
        branchService.addVehiclesToBranch("koramangala", CarType.SUV, 1, 12);
        branchService.addVehiclesToBranch("koramangala", CarType.SEDAN, 3, 10);
        branchService.addVehiclesToBranch("koramangala", CarType.BIKE, 3, 20);

        // add branch jayanagar
        Branch branch2 = branchService.addBranch("bengaluru", "jayanagar", null);
        branchService.addVehiclesToBranch("jayanagar", CarType.SEDAN, 3, 11);
        branchService.addVehiclesToBranch("jayanagar", CarType.BIKE, 3, 30);
        branchService.addVehiclesToBranch("jayanagar", CarType.HATCHBACK, 4, 8);

        // add branch malleswaram
        Branch branch3 = branchService.addBranch("bengaluru", "malleswaram", null);
        branchService.addVehiclesToBranch("malleswaram", CarType.SUV, 1, 11);
        branchService.addVehiclesToBranch("malleswaram", CarType.SEDAN, 1, 10);
        branchService.addVehiclesToBranch("malleswaram", CarType.HATCHBACK, 2, 10);


        branchService.addNewVehicleToBranch("koramangala", CarType.SEDAN, null);

        Booking booking1 = rentingService.rentAVehicle(
                    "malleswaram", 5, user1, "2023-02-20 10:00:00", "2023-02-20 12:00:00");

        Booking booking2 = rentingService.rentAVehicle(
                    "malleswaram", 5, user1, "2023-02-20 10:00:00", "2023-02-20 12:00:00");

        Booking booking3 = rentingService.rentAVehicle(
                    "koramangala", 7, user2, "2023-02-20 10:00:00", "2023-02-20 12:00:00");
        rentingService.cancelBooking(booking3.getId());

        Booking booking4 = rentingService.rentAVehicle(
                    "koramangala", 7, user2, "2023-02-20 10:00:00", "2023-02-20 12:00:00");

        rentingService.cancelBooking(booking4.getId());

        rentingService.printSystemView("2023-02-20 11:00:00", "2023-02-20 12:00:00");
    }
}

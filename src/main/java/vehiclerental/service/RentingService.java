package vehiclerental.service;

import lombok.NonNull;
import vehiclerental.enums.BookingStatus;
import vehiclerental.enums.CarType;
import vehiclerental.models.Booking;
import vehiclerental.models.Branch;
import vehiclerental.models.Interval;
import vehiclerental.models.User;
import vehiclerental.models.Vehicle;
import vehiclerental.strategy.RentingStrategyChain;
import vehiclerental.utils.DataTimeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RentingService {
    private final BranchService branchService;
    private final RentingStrategyChain rentingStrategyChain;

//    private final List<CarType> allTypes = Arrays.asList(CarType.SUV, CarType.SEDAN, CarType.HATCHBACK, CarType.BIKE);

    private final List<Booking> allBookings;

    public RentingService(
            BranchService branchService, RentingStrategyChain rentingStrategyChain) {
        this.branchService = branchService;
        this.rentingStrategyChain = rentingStrategyChain;
        this.allBookings = new ArrayList<>();
    }

    public Booking rentAVehicle(
            @NonNull String branch, @NonNull Integer noOfSeats, @NonNull User bookedBy,
            @NonNull String startTime, @NonNull String endTime) {
        System.out.println();
        long startTimeMillis = DataTimeConverter.getMillisFromDateTime(startTime);
        long endTimeMillis = DataTimeConverter.getMillisFromDateTime(endTime);
        Interval givenSlot = Interval.builder()
                .startTime(startTimeMillis)
                .endTime(endTimeMillis)
                .build();


        Map<CarType, List<Vehicle>> availableVehiclesMap =
                branchService.getAllAvailableVehicles(branch, givenSlot);
        List<CarType> eligileCarTypes = CarType.getCarTypesBySeats(noOfSeats)
                .stream()
                .filter(carType -> availableVehiclesMap.containsKey(carType))
                .collect(Collectors.toList());


        Vehicle selectedVehicle = rentingStrategyChain.getC1().chooseVehicle(eligileCarTypes, availableVehiclesMap);
        if(selectedVehicle == null) {
            System.out.println("booking cannot be completed as no vehicle is available");
            return null;
//            selectedVehicle.setStartTime(startTimeMillis);
//            selectedVehicle.setEndTime(endTimeMillis);
        }

        System.out.println("1 " + selectedVehicle.getCarType() + " from " + branch + " is booked");
        Booking booking = Booking.builder()
                .id(allBookings.size() + 1)
                .bookedBy(bookedBy)
                .bookingBranch(branchService.getBranchByName(branch))
                .bookedVehicle(selectedVehicle)
                .slot(Interval.builder()
                        .startTime(startTimeMillis)
                        .endTime(endTimeMillis)
                        .build())
//                .endTime(endTimeMillis)
                .status(BookingStatus.CREATED)
                .build();
        allBookings.add(booking);
        selectedVehicle.getBookingCalender().getBookings().add(booking);

        return booking;
    }

    public void cancelBooking(int bookingId) {
        Optional<Booking> existingBooking  = getBookingById(bookingId);
        if(!existingBooking.isPresent()) {
            System.out.println("no booking exist with id: " + bookingId);
            return;
        }

        existingBooking.get().setStatus(BookingStatus.CANCELLED);
        existingBooking.get().getBookedVehicle().getBookingCalender()
                .removeBookingFromCalender(
                        existingBooking.get());
    }

    public Optional<Booking> getBookingById(int bookingId) {
        return allBookings.stream()
                .filter(booking -> booking.getId().equals(bookingId))
                .findFirst();
    }

    public void printSystemView(String startTime, String endtime) {
        System.out.println("");
        System.out.println("");
        System.out.println("-----------");

        long startTimeMillis = DataTimeConverter.getMillisFromDateTime(startTime);
        long endTimeMillis = DataTimeConverter.getMillisFromDateTime(endtime);
        Interval givenSlot = Interval.builder()
                .startTime(startTimeMillis)
                .endTime(endTimeMillis)
                .build();

        List<Branch> allBranches = branchService.getBranches();
        System.out.println("System View");
        System.out.println("-----------");
        for(Branch b: allBranches) {
            System.out.println("branch: " + b.getName());
            Map<CarType, List<Vehicle>> availableVehiclesMap = branchService.getAllAvailableVehicles(
                    b.getName(), givenSlot);

            Set<CarType> allCarTypes = branchService.getAllCarTypes(b.getName());

            for(CarType type: allCarTypes) {
                if(!availableVehiclesMap.containsKey(type))  {
                    System.out.println("    - All " + type.name() + " are booked.");
                } else {
                    System.out.println("    - " + availableVehiclesMap.get(type).size() + " " + type.name()
                            + " is available for Rs "
                            + availableVehiclesMap.get(type).get(0).getPrice());
                }
            }
            System.out.println("");
            System.out.println("");
        }
    }
}

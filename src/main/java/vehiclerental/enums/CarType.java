package vehiclerental.enums;

import lombok.Getter;
import vehiclerental.utils.CarTypePriorityComparator;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum CarType {
    HATCHBACK(5, 2),
    SEDAN(5, 1),
    SUV(7, 1),
    BIKE(2, 1);

    private final Integer noOfSeats;
    private final Integer priority;

    CarType(int noOfSeats, int priority) {
        this.noOfSeats = noOfSeats;
        this.priority = priority;
    }

    public static List<CarType> getCarTypesBySeats(int noOfSeats) {
        return Stream.of(CarType.values())
                .filter(carType -> carType.noOfSeats.equals(noOfSeats))
                .collect(Collectors.toList());
    }

    public static Set<CarType> getCarTypesSetOrderedByPriority(List<CarType> carTypes) {
        return carTypes
                .stream()
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(new CarTypePriorityComparator())));
    }
}

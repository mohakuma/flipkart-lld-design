package vehiclerental.utils;

import vehiclerental.enums.CarType;

import java.util.Comparator;

public class CarTypePriorityComparator implements Comparator<CarType> {
    @Override
    public int compare(CarType c1, CarType c2) {
        return c1.getPriority().compareTo(c2.getPriority());
    }
}

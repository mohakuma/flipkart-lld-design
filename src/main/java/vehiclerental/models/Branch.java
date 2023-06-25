package vehiclerental.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Branch {
    private Integer id;
    private Address address;
    private String name;

    @Builder.Default
    private List<Vehicle> vehicleList = new ArrayList<>();
}

package vehiclerental.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Interval {
    private Long startTime;
    private Long endTime;
}

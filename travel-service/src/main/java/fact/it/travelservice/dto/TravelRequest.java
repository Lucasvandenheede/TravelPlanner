package fact.it.travelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelRequest {
    private String startLocation;
    private String endLocation;
    private String transportType;
    private double price;
    private String duration;
    private String departureTime;
    private String arrivalTime;
}
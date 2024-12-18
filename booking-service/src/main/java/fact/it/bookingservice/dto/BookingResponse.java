package fact.it.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfPersons;
    private double price;
    private String status;
}

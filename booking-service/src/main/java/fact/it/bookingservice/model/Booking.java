package fact.it.bookingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Bookings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

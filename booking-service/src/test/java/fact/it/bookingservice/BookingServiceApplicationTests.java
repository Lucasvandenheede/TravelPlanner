package fact.it.bookingservice;

import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.model.Booking;
import fact.it.bookingservice.repository.BookingRepository;
import fact.it.bookingservice.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceApplicationTests {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void testGetAllBookings() {
        // Arrange
        Booking booking1 = Booking.builder()
                .customerName("John Doe")
                .customerEmail("john.doe@example.com")
                .destination("Paris, France")
                .startDate(LocalDate.parse("2024-12-25"))
                .endDate(LocalDate.parse("2024-12-30"))
                .numberOfPersons(2)
                .price(1500.00)
                .status("Confirmed")
                .build();

        Booking booking2 = Booking.builder()
                .customerName("Jane Smith")
                .customerEmail("jane.smith@example.com")
                .destination("Rome, Italy")
                .startDate(LocalDate.of(2025, 1, 10))
                .endDate(LocalDate.of(2025, 1, 20))
                .numberOfPersons(4)
                .price(3000.00)
                .status("Pending")
                .build();

        List<Booking> bookings = Arrays.asList(booking1, booking2);
        when(bookingRepository.findAll()).thenReturn(bookings);

        // Act
        List<BookingResponse> bookingResponses = bookingService.getAllBookings();

        // Assert
        assertEquals(2, bookingResponses.size());
        assertEquals("John Doe", bookingResponses.get(0).getCustomerName());
        assertEquals("Paris, France", bookingResponses.get(0).getDestination());
        assertEquals("Jane Smith", bookingResponses.get(1).getCustomerName());
        assertEquals("Rome, Italy", bookingResponses.get(1).getDestination());

        verify(bookingRepository, times(1)).findAll();
    }
}

package fact.it.bookingservice.service;

import fact.it.bookingservice.dto.BookingRequest;
import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.model.Booking;
import fact.it.bookingservice.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public void createBooking(BookingRequest bookingRequest) {
        Booking booking = Booking.builder()
                .customerName(bookingRequest.getCustomerName())
                .customerEmail(bookingRequest.getCustomerEmail())
                .destination(bookingRequest.getDestination())
                .startDate(bookingRequest.getStartDate())
                .endDate(bookingRequest.getEndDate())
                .numberOfPersons(bookingRequest.getNumberOfPersons())
                .price(bookingRequest.getPrice())
                .status(bookingRequest.getStatus())
                .build();

        bookingRepository.save(booking);
    }

    // Get all bookings
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::mapToBookingResponse).toList();
    }

    // Get booking by ID
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        return mapToBookingResponse(booking);
    }

    // Update booking by ID
    public void updateBooking(Long id, BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));

        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setCustomerEmail(bookingRequest.getCustomerEmail());
        booking.setDestination(bookingRequest.getDestination());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setNumberOfPersons(bookingRequest.getNumberOfPersons());
        booking.setPrice(bookingRequest.getPrice());
        booking.setStatus(bookingRequest.getStatus());

        bookingRepository.save(booking);
    }

    // Delete booking by ID
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with id: " + id));
        bookingRepository.delete(booking);
    }

    // Helper method to map Weather entity to WeatherResponse
    private BookingResponse mapToBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .customerEmail(booking.getCustomerEmail())
                .destination(booking.getDestination())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .numberOfPersons(booking.getNumberOfPersons())
                .price(booking.getPrice())
                .status(booking.getStatus())
                .build();
    }

    @PostConstruct
    public void seedData() {
        if (bookingRepository.count() == 0) {
            Booking booking1 = Booking.builder()
                    .customerName("Alice Johnson")
                    .customerEmail("alice.johnson@example.com")
                    .destination("Rome, Italy")
                    .startDate(LocalDate.parse("2024-12-20"))
                    .endDate(LocalDate.parse("2024-12-27"))
                    .numberOfPersons(2)
                    .price(1200.00)
                    .status("Confirmed")
                    .build();

            bookingRepository.save(booking1);
        }
    }
}
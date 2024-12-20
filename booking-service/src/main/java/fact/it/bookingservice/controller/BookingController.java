package fact.it.bookingservice.controller;

import fact.it.bookingservice.dto.BookingRequest;
import fact.it.bookingservice.dto.BookingResponse;
import fact.it.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // POST: Create new booking
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBooking(@RequestBody BookingRequest bookingRequest) {
        bookingService.createBooking(bookingRequest);
    }

    // GET: Fetch all bookings
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // GET: Fetch booking by ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // PUT: Update booking by ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBooking(@PathVariable Long id, @RequestBody BookingRequest bookingRequest) {
        bookingService.updateBooking(id, bookingRequest);
    }

    // DELETE: Delete booking by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}

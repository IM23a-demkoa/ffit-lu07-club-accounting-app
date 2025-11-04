package ch.bzz.controller;

import ch.bzz.generated.api.BookingApi;
import ch.bzz.generated.model.Booking;
import ch.bzz.repository.BookingRepository;
import ch.bzz.repository.AccountRepository;
import ch.bzz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingApiController implements BookingApi {

    private final BookingRepository bookingRepository;
    private final AccountRepository accountRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @Override
    public ResponseEntity<Booking> createBooking(Booking booking) {
        // Optional: Validierungen für Accounts und Project
        var debit = accountRepository.findById(booking.getDebitAccount()).orElseThrow();
        var credit = accountRepository.findById(booking.getCreditAccount()).orElseThrow();
        var project = projectRepository.findById(booking.getProject()).orElseThrow();

        booking.setDebitAccount(debit);
        booking.setCreditAccount(credit);
        booking.setProject(project);

        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.status(201).body(savedBooking);
    }
}

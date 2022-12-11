package com.jadevirek.eventbooking.controller;

import com.jadevirek.eventbooking.facade.BookingFacade;
import com.jadevirek.eventbooking.model.dto.Event;
import com.jadevirek.eventbooking.model.dto.Ticket;
import com.jadevirek.eventbooking.model.dto.Ticket.Category;
import com.jadevirek.eventbooking.model.dto.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking/")
public class BookingController {


  private final BookingFacade bookingFacade;

  @Autowired
  public BookingController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @DeleteMapping("/event/{id}")
  public ResponseEntity<Boolean> deleteEvent(@PathVariable long id) {
    return bookingFacade.deleteEvent(id) ? ResponseEntity.ok(true)
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/events/{title}")
  public ResponseEntity<List<Event>> getEventsByTitle(@PathVariable String title) {
    return Optional.of(bookingFacade.getEventsByTitle(title, 0, 0)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @GetMapping("/event/{id}")
  public ResponseEntity<Event> getEventsById(@PathVariable long id) {
    return Optional.of(bookingFacade.getEventById(id)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @PutMapping("/event")
  public ResponseEntity<?> updateEvent(@RequestBody String event) {
    try {
      bookingFacade.updateEvent(new Event());
      return ResponseEntity.ok()
          .build();
    } catch (Exception e) {
      return ResponseEntity.notFound()
          .build();
    }
  }

  @PostMapping("/event")
  public ResponseEntity<?> createEvent(@RequestBody Event event) {
    try {
      bookingFacade.createEvent(event);
      return ResponseEntity.ok()
          .build();
    } catch (Exception e) {
      return ResponseEntity.notFound()
          .build();
    }
  }

  @GetMapping("/events/date/{day}")
  public ResponseEntity<List<Event>> getEventsForDay(
      @PathVariable @DateTimeFormat(iso = ISO.DATE) Date day,
      @RequestParam(required = false, defaultValue = "10") int pageSize,
      @RequestParam(required = false, defaultValue = "0") int pageNum) throws ParseException {
    return Optional.of(bookingFacade
        .getEventsForDay(day, pageSize, pageNum))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  /**
   * User endpoints.
   *
   * @return
   */
  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody User user) {
    try {
      bookingFacade.createUser(user);
      return ResponseEntity.ok()
          .build();
    } catch (Exception e) {
      return ResponseEntity.notFound()
          .build();
    }
  }

  @PutMapping("/user")
  public ResponseEntity<?> updateUser(@RequestBody User user) {
    try {
      bookingFacade.updateUser(user);
      return ResponseEntity.ok()
          .build();
    } catch (Exception e) {
      return ResponseEntity.notFound()
          .build();
    }
  }

  @DeleteMapping("/user/{userId}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable long userId) {
    return bookingFacade.deleteEvent(userId) ? ResponseEntity.ok(true)
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<User> getUserById(long userId) {
    return Optional.of(bookingFacade.getUserById(userId)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @GetMapping("(/user/byEmail/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    return Optional.of(bookingFacade.getUserByEmail(email)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @GetMapping("/users/{name}")
  public ResponseEntity<List<User>> getUsersByName(@PathVariable String name,
      @RequestParam(required = false, defaultValue = "10") int pageSize,
      @RequestParam(required = false, defaultValue = "0") int pageNum) {
    return Optional.of(bookingFacade
        .getUsersByName(name, pageSize, pageNum))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  /**
   * Ticket endpoints.
   */
  @PostMapping("/ticket")
  public ResponseEntity<Ticket> bookTicket(@RequestParam long userId, @RequestParam long eventId,
      @RequestParam int place,
      @RequestParam Category category) throws NoSuchFieldException {
    return Optional.of(bookingFacade.bookTicket(userId, eventId, place, category
    )).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @GetMapping("/user/tickets")
  public ResponseEntity<List<Ticket>> getBookedTickets(User user, int pageSize, int pageNum) {
    return Optional.of(bookingFacade.getBookedTickets(user, 0, 0)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @GetMapping("/event/tickets")
  public ResponseEntity<List<Ticket>> getBookedTickets(@RequestBody Event event, int pageSize,
      int pageNum) {
    return Optional.of(bookingFacade.getBookedTickets(event, 0, 0)).map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound()
            .build());
  }

  @DeleteMapping("/ticket/{ticketId}")
  public ResponseEntity<Boolean> cancelTicket(@PathVariable long ticketId) {
    return bookingFacade.cancelTicket(ticketId) ? ResponseEntity.ok(true)
        : ResponseEntity.notFound().build();
  }
}

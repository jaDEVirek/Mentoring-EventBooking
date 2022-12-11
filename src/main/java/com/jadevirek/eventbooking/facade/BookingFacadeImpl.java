package com.jadevirek.eventbooking.facade;

import com.jadevirek.eventbooking.model.dto.Event;
import com.jadevirek.eventbooking.model.dto.Ticket;
import com.jadevirek.eventbooking.model.dto.Ticket.Category;
import com.jadevirek.eventbooking.model.dto.User;
import com.jadevirek.eventbooking.service.EventService;
import com.jadevirek.eventbooking.service.TicketService;
import com.jadevirek.eventbooking.service.UserService;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingFacadeImpl implements BookingFacade {

  private final EventService eventService;
  private final TicketService ticketService;
  private final UserService userService;

  @Autowired
  public BookingFacadeImpl(EventService eventService,
      TicketService ticketService, UserService userService) {
    this.eventService = eventService;
    this.ticketService = ticketService;
    this.userService = userService;
  }

  @Override
  public Event getEventById(long eventId) {
    return eventService.get(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return eventService.getEventsByTitle(title,pageSize,pageNum);
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) throws ParseException {
    return eventService.getEventsForDay(day,pageSize,pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    return eventService.save(event);
  }

  @Override
  public Event updateEvent(Event event) {
    return eventService.update(event);
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return eventService.delete(eventId);
  }

  @Override
  public User getUserById(long userId) {
    return userService.getUserById(userId);
  }

  @Override
  public User getUserByEmail(String email) {
  return userService.getUserByEmail(email);
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return userService.getUsersByName(name,pageSize,pageNum);
  }

  @Override
  public User createUser(User user) {
    return this.userService.createUser(user);
  }

  @Override
  public User updateUser(User user) {
    return this.userService.updateUser(user);
  }

  @Override
  public boolean deleteUser(long userId) {
    return this.userService.deleteUser(userId);
  }

  @Override
  public Ticket bookTicket(long userId, long eventId, int place, Category category)
      throws NoSuchFieldException {
    return this.ticketService.bookTicket(userId,eventId,place,category);
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    return this.ticketService.getBookedTickets(user);
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    return this.ticketService.getBookedTickets(event);
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    return this.ticketService.cancelTicket(ticketId);
  }
}

package com.jadevirek.eventbooking.service;

import com.jadevirek.eventbooking.model.dao.TicketDao;
import com.jadevirek.eventbooking.model.dto.Event;
import com.jadevirek.eventbooking.model.dto.Ticket;
import com.jadevirek.eventbooking.model.dto.User;
import java.util.List;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

  private final Logger logger = LoggerFactory.getLogger(EventService.class);
  private final TicketDao ticketDao;

  @Autowired
  public TicketService(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }

  public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category)
      throws NoSuchFieldException {
    Assert.notNull(userId, "UserId can't be empty!");
    Assert.notNull(userId, "eventId can't be empty! ");
    Assert.state(place != 0, "Place must be selected");
    try {
      final Ticket newTicket = new Ticket(eventId, userId,
          category, place);
      logger.info("Save Ticket: {}", newTicket);
      ticketDao.save(newTicket);
      return newTicket;
    } catch (Exception e) {
      logger.info("Save Ticket error: {}", e.getMessage());
      throw new NoSuchFieldException();
    }
  }

  public List<Ticket> getBookedTickets(User user) {
    logger.info("Get Ticket for user: {}", user);
    Assert.notNull(user, "User can't be empty!");
    return this.ticketDao.getBookedTickets(user.getId(), "userId");
  }

  public List<Ticket> getBookedTickets(Event event) {
    logger.info("Get Ticket for event: {}", event);
    Assert.notNull(event, "User can't be empty!");
    return this.ticketDao.getBookedTickets(event.getId(), "eventId");
  }

  public boolean cancelTicket(long ticketId) {
    logger.info("Cancel Ticket with an id: {}", ticketId);
    return this.ticketDao.delete(ticketId);
  }
}

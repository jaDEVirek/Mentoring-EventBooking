package com.jadevirek.eventbooking.model.dao;

import com.jadevirek.eventbooking.handlers.eceptions.NoEntityFoundException;
import com.jadevirek.eventbooking.model.dto.Ticket;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class TicketDao implements Dao<Ticket> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Ticket get(long id) {
    final Ticket ticket = this.entityManager.find(Ticket.class, id);
    return Optional.of(ticket).orElseThrow(NoEntityFoundException::new);
  }

  @Override
  @Transactional
  public Ticket save(Ticket ticket) {
    this.entityManager.persist(ticket);
    return ticket;
  }

  @Override
  @Transactional
  public Ticket update(Ticket ticket) {
    return entityManager.merge(ticket);
  }

  @Override
  @Transactional
  public boolean delete(long ticketId) {
    final Ticket ticket = entityManager.find(Ticket.class, ticketId);
    if (ticket == null) {
      return false;
    }
    this.entityManager.remove(ticket);
    return true;
  }

  public List<Ticket> getBookedTickets(long id, String entityId) {
    return entityManager
        .createQuery("Select t From Ticket t WHERE t." + entityId + "= ?1", Ticket.class)
        .setParameter(1, id)
        .getResultList();
  }
}

package com.jadevirek.eventbooking.model.dao;

import com.jadevirek.eventbooking.handlers.eceptions.NoEntityFoundException;
import com.jadevirek.eventbooking.model.dto.Event;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class EventDao implements Dao<Event> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public Event get(long id) {
    final Event event = this.entityManager.find(Event.class, id);
    return Optional.of(event).orElseThrow(NoEntityFoundException::new);
  }

  @Override
  @Transactional
  public Event save(Event event) {
    this.entityManager.persist(event);
    return event;
  }

  @Override
  @Transactional
  public Event update(Event event) {
    return this.entityManager.merge(event);
  }

  @Override
  public boolean delete(long eventId) {
    final Event event = entityManager.find(Event.class, eventId);
    if (event == null) {
      return false;
    }
    this.entityManager.remove(event);
    return true;
  }

  public List<Event> getEventsForDay(String day, int pageSize, int pageNum) {
    return entityManager
        .createQuery("  ="+ "DATE"+ "'?1'", Event.class)
        .setFirstResult(pageNum * pageSize).setMaxResults(pageSize).setParameter(1, day)
        .getResultList();
  }

  public List<Event> getEventsByTitle(String title, int defaultPageSize, int pageNum){
    return entityManager
        .createQuery("Select e From Event e WHERE e.title = ?1", Event.class)
        .setFirstResult(pageNum * defaultPageSize).setMaxResults(defaultPageSize).setParameter(1, title)
        .getResultList();

  }
}

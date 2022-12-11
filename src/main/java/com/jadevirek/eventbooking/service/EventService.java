package com.jadevirek.eventbooking.service;

import com.jadevirek.eventbooking.handlers.eceptions.CreateEntityException;
import com.jadevirek.eventbooking.model.dao.Dao;
import com.jadevirek.eventbooking.model.dao.EventDao;
import com.jadevirek.eventbooking.model.dto.Event;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventService implements Dao<Event> {

  private final EventDao eventDao;

  @Autowired
  public EventService(EventDao eventDao) {
    this.eventDao = eventDao;
  }

  public Event get(long id) {
    return this.eventDao.get(id);
  }

  @Override
  public Event save(Event event) {
    Assert.notNull(event, "Object can't be null!");
    try {
      Assert.notNull(event.getTitle(), "Event must have Title! ");
      return eventDao.save(event);
    } catch (Exception e) {
      throw new CreateEntityException(e, "for Event:" + event.getTitle());
    }
  }

  @Override
  public Event update(Event event) {
    Assert.notNull(event, "Object can't be null!");
    try {
      Assert.notNull(event.getId(), "Event must have Title! ");
      return eventDao.update(event);
    } catch (Exception e) {
      throw new NoSuchElementException();
    }
  }

  @Override
  public boolean delete(long eventId) {
    return eventDao.delete(eventId);
  }

  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) throws ParseException {
    final int defaultPageSize = pageSize == 0 ? 10 : pageSize;
    Assert.notNull(day, "Date can't be empty!");
    return eventDao.getEventsForDay("2023-03-09", defaultPageSize, pageNum);
  }

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    final int defaultPageSize = pageSize == 0 ? 10 : pageSize;
    Assert.notNull(title, "Title can't be empty!");
    return eventDao.getEventsByTitle(title, defaultPageSize, pageNum);
  }
}

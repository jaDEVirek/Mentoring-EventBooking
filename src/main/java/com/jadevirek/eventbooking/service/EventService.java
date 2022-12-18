package com.jadevirek.eventbooking.service;

import com.jadevirek.eventbooking.handlers.eceptions.CreateEntityException;
import com.jadevirek.eventbooking.model.dao.Dao;
import com.jadevirek.eventbooking.model.dao.EventDao;
import com.jadevirek.eventbooking.model.dto.Event;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EventService implements Dao<Event> {

  private final Logger logger = LoggerFactory.getLogger(EventService.class);
  private final EventDao eventDao;

  @Autowired
  public EventService(EventDao eventDao) {
    this.eventDao = eventDao;
  }

  public Event get(long id) {
    logger.info("Get Event with an id: {}", id);
    return this.eventDao.get(id);
  }

  @Override
  public Event save(Event event) {
    Assert.notNull(event, "Object can't be null!");
    try {
      Assert.notNull(event.getTitle(), "Event must have Title! ");
      logger.info("Save Event: {}", event);
      return eventDao.save(event);
    } catch (Exception e) {
      logger.info("Save Event error: {}", e.getMessage());
      throw new CreateEntityException(e, "for Event:" + event.getTitle());
    }
  }

  @Override
  public Event update(Event event) {
    Assert.notNull(event, "Object can't be null!");
    try {
      Assert.notNull(event.getId(), "Event must have Title! ");
      logger.info("Update Event: {}", event);
      return eventDao.update(event);
    } catch (Exception e) {
      logger.info("Update Event error: {}", e.getMessage());
      throw new NoSuchElementException();
    }
  }

  @Override
  public boolean delete(long eventId) {
    logger.info("Delete Event with an id: {}", eventId);
    return eventDao.delete(eventId);
  }

  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    logger.info("Get Events for day: {}", day);
    final int defaultPageSize = pageSize == 0 ? 10 : pageSize;
    Assert.notNull(day, "Date can't be empty!");
    final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return eventDao.getEventsForDay(formatter.format(day), defaultPageSize, pageNum);
  }

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    logger.info("Get Events by title: {}", title);
    final int defaultPageSize = pageSize == 0 ? 10 : pageSize;
    Assert.notNull(title, "Title can't be empty!");
    return eventDao.getEventsByTitle(title, defaultPageSize, pageNum);
  }
}

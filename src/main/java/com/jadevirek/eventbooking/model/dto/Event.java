package com.jadevirek.eventbooking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jadevirek.eventbooking.handlers.DateHandler;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Events")
@Getter
@Setter
@EqualsAndHashCode
public class Event implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  @JsonDeserialize(using = DateHandler.class)
  private Date date;

  public Event(String title, Date date) {
    this.title = title;
    this.date = date;
  }

  public Event() {
  }
}

package com.jadevirek.eventbooking.model.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Tickets")
@Getter
@Setter
@EqualsAndHashCode
public class Ticket implements Serializable {

  public enum Category {
    STANDARD("Standard"), PREMIUM("Premium"), BAR("Bar");
    Category(String premium) {
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long eventId;
  private long userId;
  private Category category;
  private int place;

  public Ticket(long eventId, long userId,
      Category category, int place) {
    this.eventId = eventId;
    this.userId = userId;
    this.category = category;
    this.place = place;
  }

  public Ticket() {
  }
}

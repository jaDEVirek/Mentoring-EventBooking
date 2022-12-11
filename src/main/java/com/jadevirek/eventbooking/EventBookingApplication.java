package com.jadevirek.eventbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class EventBookingApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventBookingApplication.class, args);
  }

}

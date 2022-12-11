package com.jadevirek.eventbooking.handlers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DateHandler extends StdDeserializer<Date> {

  public DateHandler() {
    this(null);
  }

  public DateHandler(Class<?> clazz) {
    super(clazz);
  }

  @Override
  public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException {
    String date = jsonparser.getText();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      return sdf.parse(date);
    } catch (Exception e) {
      return null;
    }
  }
}


package com.jadevirek.eventbooking.model.dao;


public interface Dao<T> {

  T get(long id);

  T save(T t);

  T update(T t);

  boolean delete(long id);
}

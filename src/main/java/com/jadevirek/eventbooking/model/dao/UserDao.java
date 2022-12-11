package com.jadevirek.eventbooking.model.dao;


import com.jadevirek.eventbooking.handlers.eceptions.NoEntityFoundException;
import com.jadevirek.eventbooking.model.dto.Event;
import com.jadevirek.eventbooking.model.dto.User;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class UserDao implements Dao<User> {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public User get(long id) {
    final User user = this.entityManager.find(User.class, id);
    return Optional.of(user).orElseThrow(NoEntityFoundException::new);
  }

  @Override
  @Transactional
  public User save(User user) {
    this.entityManager.persist(user);
    return user;
  }

  @Override
  public User update(User user) {
    return this.entityManager.merge(user);
  }

  @Override
  @Transactional
  public boolean delete(long userId) {
    final User user = entityManager.find(User.class, userId);
    if (user == null) {
      return false;
    }
    this.entityManager.remove(user);
    return true;
  }

  public User getUserByEmail(String email) {
    return entityManager
        .createQuery("Select u From User u WHERE u.email = ?1", User.class)
        .setParameter(1, email)
        .getSingleResult();
  }

  public List<User> getUserByName(String name) {
    return entityManager
        .createQuery("Select u From User u WHERE u.name = ?1", User.class)
        .setParameter(1, name)
        .getResultList();
  }
}


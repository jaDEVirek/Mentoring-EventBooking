package com.jadevirek.eventbooking.service;

import com.jadevirek.eventbooking.handlers.eceptions.CreateEntityException;
import com.jadevirek.eventbooking.model.dao.UserDao;
import com.jadevirek.eventbooking.model.dto.User;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService {

  private final UserDao userDao;

  @Autowired
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return userDao.getUserByName(name);
  }

  public User createUser(User user) {
    Assert.notNull(user, "Object can't be null!");
    try {
      Assert.notNull(user.getName(), "User must have Name! ");
      return userDao.save(user);
    } catch (Exception e) {
      throw new CreateEntityException(e, "for User: " + user.getName());
    }
  }

  public User updateUser(User user) {
    Assert.notNull(user, "Object can't be null!");
    try {
      Assert.notNull(user.getName(), "User must have Name! ");
      return userDao.update(user);
    } catch (Exception e) {
      throw new NoSuchElementException();
    }
  }

  public boolean deleteUser(long userId) {
    return userDao.delete(userId);
  }

  public User getUserByEmail(String email) {
    Assert.isTrue(EmailValidator.getInstance().isValid(email), "Email is not valid!");
    return userDao.getUserByEmail(email);
  }

  public User getUserById(long userId) {
    return userDao.get(userId);
  }
}

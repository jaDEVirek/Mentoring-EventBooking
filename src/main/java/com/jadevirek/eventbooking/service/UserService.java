package com.jadevirek.eventbooking.service;

import com.jadevirek.eventbooking.handlers.eceptions.CreateEntityException;
import com.jadevirek.eventbooking.model.dao.UserDao;
import com.jadevirek.eventbooking.model.dto.User;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);
  private final UserDao userDao;

  @Autowired
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    logger.info("Get User by name: {}", name);
    return userDao.getUserByName(name);
  }

  public User createUser(User user) {
    Assert.notNull(user, "Object can't be null!");
    try {
      Assert.notNull(user.getName(), "User must have Name! ");
      logger.info("Save User: {}", user);
      return userDao.save(user);
    } catch (Exception e) {
      logger.info("Save User error: {}", e.getMessage());
      throw new CreateEntityException(e, "for User: " + user.getName());
    }
  }

  public User updateUser(User user) {
    Assert.notNull(user, "Object can't be null!");
    try {
      Assert.notNull(user.getName(), "User must have Name! ");
      logger.info("Update User: {}", user);
      return userDao.update(user);
    } catch (Exception e) {
      logger.info("Update User error: {}", e.getMessage());
      throw new NoSuchElementException();
    }
  }

  public boolean deleteUser(long userId) {
    logger.info("Delete User with an id:: {}", userId);
    return userDao.delete(userId);
  }

  public User getUserByEmail(String email) {
    Assert.isTrue(EmailValidator.getInstance().isValid(email), "Email is not valid!");
    logger.info("Get User by email: {}", email);
    return userDao.getUserByEmail(email);
  }

  public User getUserById(long userId) {
    logger.info("Get User by id: {}", userId);
    return userDao.get(userId);
  }
}

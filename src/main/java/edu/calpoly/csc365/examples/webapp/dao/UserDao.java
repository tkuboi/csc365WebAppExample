package edu.calpoly.csc365.examples.webapp.dao;

import edu.calpoly.csc365.examples.webapp.entity.User;

public interface UserDao extends Dao<User> {
  public Boolean authenticate(String name, String pass);
}

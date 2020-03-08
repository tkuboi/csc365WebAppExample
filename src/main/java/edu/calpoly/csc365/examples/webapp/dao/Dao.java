package edu.calpoly.csc365.examples.webapp.dao;

import java.util.Set;

public interface Dao<T> {
  T getById(int id);
  Set<T> getAll();
  Integer insert(T obj);
  Integer update(T obj);
  Integer delete(T obj);
}

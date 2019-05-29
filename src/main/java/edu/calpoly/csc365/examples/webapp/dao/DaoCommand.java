package edu.calpoly.csc365.examples.webapp.dao;

public interface DaoCommand {
  Object execute(DaoManager daoManager);
}

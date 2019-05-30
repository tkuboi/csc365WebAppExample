package edu.calpoly.csc365.examples.webapp.controller;

import edu.calpoly.csc365.examples.webapp.dao.ConnectionFactory;
import edu.calpoly.csc365.examples.webapp.dao.CustomerDaoImpl;
import edu.calpoly.csc365.examples.webapp.dao.Dao;
import edu.calpoly.csc365.examples.webapp.dao.DaoManager;
import edu.calpoly.csc365.examples.webapp.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

  private DaoManager dm;
  private Dao<Customer> customerDao;

  public CustomerServlet() throws Exception {
    dm = ConnectionFactory.createDaoManager();
    customerDao = dm.getCustomerDao();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Set<Customer> customers = customerDao.getAll();
    request.setAttribute("customers", customers);
    request.setAttribute("message", "Hello World");
    request.getRequestDispatcher("customer.jsp").forward(request, response);
  }
}

package edu.calpoly.csc365.examples.webapp.controller;

import edu.calpoly.csc365.examples.webapp.dao.CustomerDaoImpl;
import edu.calpoly.csc365.examples.webapp.dao.Dao;
import edu.calpoly.csc365.examples.webapp.dao.DaoManager;
import edu.calpoly.csc365.examples.webapp.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "CustomerServlet")
public class CustomerServlet extends HttpServlet {

  DaoManager dm;
  Dao<Customer> customerDao;

  public CustomerServlet() throws Exception {
    dm = DaoManager.getInstance();
    customerDao = dm.getCustomerDao();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Set<Customer> customers = customerDao.getAll();
    //Set<Customer> customers = new HashSet<Customer>();
    List<Customer> custlist = new ArrayList<Customer>();
    Iterator custItr = customers.iterator();
    while (custItr.hasNext()) {
      custlist.add((Customer)custItr.next());
    }
    custlist.add(new Customer(0, 123, "HenoHeno", "here", "phone"));
    request.setAttribute("customers", custlist);
    request.setAttribute("message", "Hello World");
    request.getRequestDispatcher("customer.jsp").forward(request, response);
  }
}

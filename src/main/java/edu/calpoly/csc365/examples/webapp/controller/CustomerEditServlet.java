package edu.calpoly.csc365.examples.webapp.controller;

import edu.calpoly.csc365.examples.webapp.dao.ConnectionFactory;
import edu.calpoly.csc365.examples.webapp.dao.Dao;
import edu.calpoly.csc365.examples.webapp.dao.DaoManager;
import edu.calpoly.csc365.examples.webapp.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerEditServlet", urlPatterns = "/edit_customer")
public class CustomerEditServlet extends HttpServlet {
  private DaoManager dm;
  private Dao<Customer> customerDao;

  public CustomerEditServlet() throws Exception {
    dm = ConnectionFactory.createDaoManager();
    customerDao = dm.getCustomerDao();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int ssn = Integer.parseInt(request.getParameter("ssn"));
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    String address = request.getParameter("address");
    String phone = request.getParameter("phone");
    Customer customer = new Customer(id, ssn, name, address, phone);
    this.customerDao.update(customer);
    request.setAttribute("customer", customer);
    request.getRequestDispatcher("customer_form.jsp").forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String id = request.getParameter("id");
    Customer customer = this.customerDao.getById(Integer.parseInt(id));
    request.setAttribute("customer", customer);
    request.getRequestDispatcher("customer_form.jsp").forward(request, response);
  }
}

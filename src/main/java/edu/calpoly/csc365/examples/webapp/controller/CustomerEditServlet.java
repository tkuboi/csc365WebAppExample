package edu.calpoly.csc365.examples.webapp.controller;

import edu.calpoly.csc365.examples.webapp.dao.DaoManagerFactory;
import edu.calpoly.csc365.examples.webapp.dao.Dao;
import edu.calpoly.csc365.examples.webapp.dao.DaoManager;
import edu.calpoly.csc365.examples.webapp.entity.Customer;
import edu.calpoly.csc365.examples.webapp.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerEditServlet", urlPatterns = "/edit_customer")
public class CustomerEditServlet extends HttpServlet {
  private DaoManager dm;
  private Dao<Customer> customerDao;

  public CustomerEditServlet() throws Exception {
    dm = DaoManagerFactory.createDaoManager();
    customerDao = dm.getCustomerDao();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie loginCookie = AuthenticationService.getLoginCookie(request);
    if (loginCookie == null) {
      response.sendRedirect("login");
    } else {
      response.addCookie(loginCookie);
      Integer id = Integer.parseInt(request.getParameter("id"));
      String ssn = request.getParameter("ssn");
      String name = request.getParameter("name");
      String address = request.getParameter("address");
      String phone = request.getParameter("phone");
      Customer customer = new Customer(id, ssn, name, address, phone);
      this.customerDao.update(customer);
      request.setAttribute("customer", customer);
      request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie loginCookie = AuthenticationService.getLoginCookie(request);
    if (loginCookie == null) {
      response.sendRedirect("login");
    } else {
      response.addCookie(loginCookie);
      String id = request.getParameter("id");
      Customer customer = this.customerDao.getById(Integer.parseInt(id));
      request.setAttribute("customer", customer);
      request.getRequestDispatcher("customer_edit.jsp").forward(request, response);
    }
  }
}

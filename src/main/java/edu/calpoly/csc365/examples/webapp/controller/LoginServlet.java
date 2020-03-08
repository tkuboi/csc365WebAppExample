package edu.calpoly.csc365.examples.webapp.controller;

import edu.calpoly.csc365.examples.webapp.dao.Dao;
import edu.calpoly.csc365.examples.webapp.dao.DaoManager;
import edu.calpoly.csc365.examples.webapp.dao.DaoManagerFactory;
import edu.calpoly.csc365.examples.webapp.dao.UserDao;
import edu.calpoly.csc365.examples.webapp.entity.Customer;
import edu.calpoly.csc365.examples.webapp.entity.User;
import edu.calpoly.csc365.examples.webapp.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
  private DaoManager dm = null;
  private AuthenticationService authenticationService = null;

  public LoginServlet() throws Exception {
    dm = DaoManagerFactory.createDaoManager();
    authenticationService = new AuthenticationService(dm.getUserDao());
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("login.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    if (authenticationService.authenticate(name, pass)) {
      Cookie loginCookie = AuthenticationService.createLoginCookie(name);
      response.addCookie(loginCookie);
      response.sendRedirect("customers");
    } else {
      response.sendRedirect("login");
    }
  }

}

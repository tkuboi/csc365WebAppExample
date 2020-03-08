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

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
  private DaoManager dm = null;
  private AuthenticationService authenticationService = null;

  public LogoutServlet() throws Exception {
    dm = DaoManagerFactory.createDaoManager();
    authenticationService = new AuthenticationService(dm.getUserDao());
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie loginCookie = AuthenticationService.getLoginCookie(request);
    if (loginCookie != null) {
      loginCookie.setMaxAge(0);
      response.addCookie(loginCookie);
    }
    response.sendRedirect("login");
  }
}

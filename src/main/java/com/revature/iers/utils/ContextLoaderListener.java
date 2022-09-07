package com.revature.iers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.iers.daos.ReimbursementDAO;
import com.revature.iers.daos.UserDAO;
import com.revature.iers.services.ReimbursementService;
import com.revature.iers.services.TokenService;
import com.revature.iers.services.UserService;
import com.revature.iers.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* ObjectMapper provides functionality for reading and writing JSON, either to and from basic POJOs (Plain Old Java Objects) */
        ObjectMapper mapper = new ObjectMapper();

        /* Dependency Injection */
        TestServlet testServlet = new TestServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()));
        UserServlet userServlet = new UserServlet(mapper, new UserService(new UserDAO()));
        AuthServlet authServlet = new AuthServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()));
        ReimbursementServlet reimbursementServlet = new ReimbursementServlet(mapper, new TokenService(new JwtConfig()), new ReimbursementService(new ReimbursementDAO()));
        ViewReimbursementFilterServlet viewReimbursementFilterServlet = new ViewReimbursementFilterServlet(mapper, new TokenService(new JwtConfig()), new ReimbursementService(new ReimbursementDAO()));
        AdminServlet adminServlet = new AdminServlet(mapper, new TokenService(new JwtConfig()), new UserService(new UserDAO()));

        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("TestServlet", testServlet).addMapping("/test");
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("ReimbursementServlet", reimbursementServlet).addMapping(("/reimbursement/*"));
        context.addServlet("AdminServlet", adminServlet).addMapping("/admin/*");
        context.addServlet("ViewReimbursementFilterServlet", viewReimbursementFilterServlet).addMapping("/reimbursementview/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nShutting down IERS web application");
    }
}

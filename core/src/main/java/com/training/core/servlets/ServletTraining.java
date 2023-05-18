package com.training.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service= {Servlet.class})
@SlingServletResourceTypes(
        resourceTypes="training/components/xyz",

        methods= HttpConstants.METHOD_GET,
        extensions="html"
)
public class ServletTraining extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        PrintWriter out = response.getWriter();

        out.println("This is my first servlet");


    }
}

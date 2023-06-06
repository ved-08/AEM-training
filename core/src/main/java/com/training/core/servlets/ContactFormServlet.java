package com.training.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.day.cq.commons.jcr.JcrUtil;


@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths=" + "/bin/contactform", "sling.servlet.extensions=" + "html" })

public class ContactFormServlet extends SlingAllMethodsServlet{

    private static final long serialVersionUID = 1L;

    private static final String CONTACT_US_PATH = "/content/usergenerated/contact-us";



    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);

            // Get form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String country=request.getParameter("country");
            String state=request.getParameter("state");
            String message = request.getParameter("message");

            // Create path in the repository for contact form submissions
            Node contactUsNode = JcrUtil.createPath(CONTACT_US_PATH, "cq:Page", session);
            Node submissionNode = JcrUtil.createUniquePath(contactUsNode.getPath() + "/submission_" + (new Date().getTime()), "nt:unstructured", session);

            // Store form data in repository
            submissionNode.setProperty("name", name);
            submissionNode.setProperty("email", email);
            submissionNode.setProperty("country", country);
            submissionNode.setProperty("state", state);
            submissionNode.setProperty("message", message);
            session.save();

            // Return success response
            JSONObject responseJson = new JSONObject();
//            responseJson.put("success", true);
//            responseJson.put("message", "Form submission successful");
//            out.println(responseJson.toString());

        } catch (Exception e) {
            // Return error response
           e.printStackTrace();
        }

        out.flush();
        out.close();
    }



}

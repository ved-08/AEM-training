package com.training.core.servlets;

import com.day.cq.commons.jcr.JcrUtil;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths=" + "/bin/signup", "sling.servlet.extensions=" + "html" })

public class SignupServlet extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 1L;

    private static final String CONTACT_US_PATH = "/content/users";

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();



        try {
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);

            // Get form data
            String name = request.getParameter("username");
            String email = request.getParameter("password");
            String message = request.getParameter("email");

            // Create path in the repository for contact form submissions
            Node contactUsNode = JcrUtil.createPath(CONTACT_US_PATH, "cq:Page", session);
            Node submissionNode = JcrUtil.createUniquePath(contactUsNode.getPath() + "/submission_" + (new Date().getTime()), "nt:unstructured", session);

            // Store form data in repository
            submissionNode.setProperty("username", name);
            submissionNode.setProperty("email", email);
            submissionNode.setProperty("password", message);
            session.save();

            // Return success response
            JSONObject responseJson = new JSONObject();
            responseJson.put("success", true);
            responseJson.put("message", "Form submission successful");
            out.println(responseJson.toString());

        } catch (Exception e) {
            // Return error response
            e.printStackTrace();
        }

        out.flush();
        out.close();
    }




}


package com.training.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
        service=Servlet.class, property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_POST,
                "sling.servlet.paths=" + "/bin/login",
                "sling.servlet.extensions=" + "html"
        })
public class LoginServlet extends SlingAllMethodsServlet {
    String path="/content/user";
    @Override
    protected void doPost(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
        Resource resource=request.getResourceResolver().getResource(path);
        if(resource!=null){

        }
        else{
            response.getWriter().write("Resource(resource object) on line 24 not found");

        }
    }
}

package com.training.core.servlets;

import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.json.JsonObject;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;




@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "training/components/structure/search", methods = HttpConstants.METHOD_GET,
        selectors = {"search" }, extensions = "json")
@ServiceDescription("Search Query Servlet")

//@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
//        "sling.servlet.paths=" + "/bin/searchquery", "sling.servlet.extensions=" + "json" })

public class SearchServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(SearchServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            log.info("in try block");
            ResourceResolver resourceResolver = request.getResourceResolver();
            Session session = resourceResolver.adaptTo(Session.class);
            String queryString = "SELECT * FROM [cq:Page] AS page WHERE ISDESCENDANTNODE(page, \"/content/training/us/en\")";
            Query query = session.getWorkspace().getQueryManager().createQuery(queryString, Query.JCR_SQL2);
            QueryResult searchResult= (QueryResult) query.execute();
            NodeIterator nodeIterator = searchResult.getNodes();



            JSONArray jsonArray = new JSONArray();
            while (nodeIterator.hasNext()) {

                Node pageNode = nodeIterator.nextNode();
                String title = "";
                if (pageNode.hasProperty("jcr:title")) {
                    title = pageNode.getProperty("jcr:title").getString();
                }
                String path = pageNode.getPath();
                log.info("page is {}");
                if (!title.isEmpty()) {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title", title);
                    jsonObject.put("path", path);

                    jsonArray.put(jsonObject);

                }
            }





            String jsonResponse = jsonArray.toString();

            response.getWriter().write(jsonResponse);

            session.logout();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

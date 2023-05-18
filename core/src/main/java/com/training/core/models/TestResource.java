package com.training.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;


@Model(adaptables = Resource.class)
public class TestResource {
    private String pg;

    @Self
    Resource resource;



    @PostConstruct
    protected void init() {
        ResourceResolver resourceResolver = resource.getResourceResolver();

        Resource pageResource = resourceResolver.getResource("/content/training/us/en/practice-page");
        Page page= pageResource.adaptTo(Page.class);

        if(page != null){

            pg="Successful";
        }
        else{
            pg="Unsuccessful";
        }
    }

    public void setPg(String pg) {
        this.pg = pg;
    }
    public String getPg() {
        return pg;
    }


}

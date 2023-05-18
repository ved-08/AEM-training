package com.training.core.models;



import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;



import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;



@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
         resourceType = "/apps/training/components/structure/dynamicList",
         defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DynamicList {



         @SlingObject
 private Resource currentResource;



         @SlingObject
 private ResourceResolver resourceResolver;



 private List<Page> pg = new ArrayList<>();



         @PostConstruct
 protected void init() {
 PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
 Page currentPage = Optional.ofNullable(pageManager)
 .map(pm -> pm.getContainingPage(currentResource))
 .orElse(null);



 if (currentPage != null) {
 Iterator<Page> childPages = currentPage.listChildren();
 while (childPages.hasNext()) {
 Page childPage = childPages.next();
 // do something with the child page, e.g. create a ChildPage Sling model'
 pg.add(childPage);
 }
 }
 }



         public List<Page> getPg() {
            return pg;
        }
}
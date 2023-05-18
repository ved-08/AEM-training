package com.training.core.models;

import javax.inject.Inject;



import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChildPage {



         @Self
 private Resource resource;



         @Inject
 private String title;



         @Inject
 private String description;



         public String getTitle() {
            return title;
    }



         public String getDescription() {
            return description;
 }
}

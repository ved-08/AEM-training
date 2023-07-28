package com.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//import org.osgi.service.log.LoggerFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

    private final Logger LOG = LoggerFactory.getLogger(FooterModel.class);
    @Self
    Resource resource;

    ValueMap valuemap = resource.getValueMap();
    List<MultiFieldHelper> column = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            Resource columns = resource.getChild("options");
            if (columns != null) {
                for (Resource columnNode : columns.getChildren()) {
                    MultiFieldHelper multiFieldHelper = new MultiFieldHelper(columnNode);
                    if (columnNode.hasChildren()) {
                        multiFieldHelper.setColumnTitle(columnNode.getValueMap().get("columnTitle", String.class));

                        multiFieldHelper.setColumnType(columnNode.getValueMap().get("columnType", String.class));


                        if (multiFieldHelper.getColumnType().equalsIgnoreCase("link")) {
                            List<Link> li = new ArrayList<>();
                            Resource linkResource = columnNode.getChild("optionLink");
                            for (Resource linkChildResource : linkResource.getChildren()) {
                                li.add(new Link(linkChildResource));
                            }
                            multiFieldHelper.setLinks(li);
                        }
                        if (multiFieldHelper.getColumnType().equalsIgnoreCase("text")) {
                            multiFieldHelper.setColumnText(columnNode.getValueMap().get("textField", String.class));
                        }

                    }
                    column.add(multiFieldHelper);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}

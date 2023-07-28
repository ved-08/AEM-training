package com.training.core.models;


import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

public class Link {
    private String linkTitle;
    private String linkPath;


    public Link(Resource resource){

        if(StringUtils.isNotBlank(resource.getValueMap().get("subHeading", String.class))){
            this.linkTitle=resource.getValueMap().get("subHeading", String.class);
        }
        if(StringUtils.isNotBlank(resource.getValueMap().get("link", String.class))){
            this.linkPath=resource.getValueMap().get("link", String.class);
        }
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }
}

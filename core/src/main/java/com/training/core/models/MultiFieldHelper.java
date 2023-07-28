package com.training.core.models;

import com.training.core.models.Link;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;

import java.util.List;


public class MultiFieldHelper {

    private String columnTitle;
    private String columnType;
    private String columnText;
    private List<Link> links;

    public MultiFieldHelper(Resource resource){
        if(StringUtils.isNotBlank(resource.getValueMap().get("columnTitle", String.class))){
            this.columnTitle=resource.getValueMap().get("subheading", String.class);
        }

        if(StringUtils.isNotBlank(resource.getValueMap().get("columnType", String.class))){
            this.columnTitle=resource.getValueMap().get("columnType", String.class);
        }
        if(StringUtils.isNotBlank(resource.getValueMap().get("columnText", String.class))){
            this.columnText=resource.getValueMap().get("columnText", String.class);
        }


    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(String columnTitle) {
        this.columnTitle = columnTitle;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnText() {
        return columnText;
    }

    public void setColumnText(String columnText) {
        this.columnText = columnText;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

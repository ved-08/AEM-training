package com.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class LogoModel {
    @Inject
    @Optional
    private String logoUrl;

    @Inject
    @Optional
    private String altText;

    public void init() {
        //Business logic or any other initialization will come here
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getAltText() {
        return altText;
    }

}

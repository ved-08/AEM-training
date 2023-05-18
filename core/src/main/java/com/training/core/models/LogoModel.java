package com.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class)
public class LogoModel {
    @Inject
    @Optional
    @Named("logoUrl")
    private String imageUrl;

    @Inject
    @Optional
    @Named("altText")
    private String altImgText;

    public void init() {
        //Business logic or any other initialization will come here
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAltImgText() {
        return altImgText;
    }
}

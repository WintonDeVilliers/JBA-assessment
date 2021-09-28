package com.justbrandsafrica.assessment.config;


import com.justbrandsafrica.assessment.service.ProfileCheckerService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(ProfileCheckerService.class);
    }
}
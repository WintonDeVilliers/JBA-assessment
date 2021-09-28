package com.justbrandsafrica.assessment.service;

import com.justbrandsafrica.assessment.model.ProfileHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@Path("assessment")
public class ProfileCheckerService {

    Logger logger = LoggerFactory.getLogger(ProfileCheckerService.class);

    @POST
    @Path("phase1")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkMandatoryFields(ProfileHolder profile) {

        String[] requiredFields = {"dob", "idNumber", "gender","address","provinces", "schools", "grades","serialNumber", "imei"};

        List<String> fieldsStillRequired = new ArrayList<>();

        for(String requiredField: requiredFields)
        {
            Field field = null;
            try {
                field = profile.getProfile().getClass().getDeclaredField(requiredField);
                field.setAccessible(true);
                Object value = field.get(profile.getProfile());

                if(value==null){
                    fieldsStillRequired.add(requiredField);
                }

                System.out.println("value : " +value);
            } catch (NoSuchFieldException | IllegalAccessException e) {

                System.out.println("No such field exists.");

                e.printStackTrace();
            }

        }

        return Response.ok(fieldsStillRequired).build();
    }

    @POST
    @Path("phase2")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkAndUpdateMandatoryFields(ProfileHolder profile) {

        String[] requiredFields = {"dob", "idNumber", "gender","address","provinces", "schools", "grades","serialNumber", "imei"};

        List<String> fieldsStillRequired = new ArrayList<>();

        for(String requiredField: requiredFields)
        {
            Field field = null;
            try {
                field = profile.getProfile().getClass().getDeclaredField(requiredField);
                field.setAccessible(true);
                Object value = field.get(profile.getProfile());

                if(value==null){
                    fieldsStillRequired.add(requiredField);
                }

                System.out.println("value : " +value);
            } catch (NoSuchFieldException | IllegalAccessException e) {

                System.out.println("No such field exists.");

                e.printStackTrace();
            }

        }

        for(String requiredField: fieldsStillRequired)
        {
            Field field = null;
            try {
                field = profile.getProfile().getClass().getDeclaredField(requiredField);
                field.setAccessible(true);
                Object value = field.get(profile.getProfile());

                if(value==null){
                    field.set(profile.getProfile(),"Updated");
                }

                System.out.println("value : " +value);
            } catch (NoSuchFieldException | IllegalAccessException e) {

                System.out.println("No such field exists.");

                e.printStackTrace();
            }

        }

        return Response.ok(profile).build();
    }

}

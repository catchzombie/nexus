package com.catchzombie.api.resource.impl;

import com.catchzombie.services.profile.ProfileService;
import com.catchzombie.request.impl.ProfileRequest;
import com.catchzombie.api.resource.CzBaseResource;
import com.catchzombie.response.CzResponse;
import com.catchzombie.response.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shubham on 5/2/17.
 */
@RestController
@RequestMapping("/profile")
public class ProfileResource extends CzBaseResource {


    private final ProfileService profileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileResource.class);

    @Autowired
    public ProfileResource(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateProfile")
    public CzResponse updateProfile(@RequestBody ProfileRequest profileRequest) {
        validateRequest(profileRequest);
        LOGGER.info(String.format("Received request to update profile", profileRequest));
        try {
            profileService.updateProfile(profileRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            LOGGER.error("Unable to update profile", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.POST, value = "/createProfile")
    public CzResponse createProfile(@RequestBody ProfileRequest profileRequest) {
        validateRequest(profileRequest);
        LOGGER.info(String.format("Received request to create profile", profileRequest));
        try {
            profileService.createProfile(profileRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            LOGGER.error("Unable to create profile", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }
    }


    @RequestMapping("/getProfile")
    public CzResponse getProfile(@RequestParam String userId) {
        validateNonNullRequestParams(userId);
        LOGGER.info(String.format("Received request to get profile for id %s", userId));
        try {
            return new CzResponse(ResponseStatus.SUCCESS,profileService.getProfile(userId));
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to fetch profile for user %s", userId));
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProfile")
    public CzResponse deleteProfile(@RequestParam String userId) {
        validateNonNullRequestParams(userId);
        LOGGER.info(String.format("Received request to delete profile for id %s", userId));
        try {
            profileService.deleteProfile(userId);
            return new CzResponse(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to delete profile for user %s", userId));
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }
    }


}

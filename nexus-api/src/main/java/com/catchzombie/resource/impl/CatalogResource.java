package com.catchzombie.resource.impl;

import com.catchzombie.CatalogService;
import com.catchzombie.request.impl.CategoryRequest;
import com.catchzombie.request.impl.SwipeRequest;
import com.catchzombie.resource.CzBaseResource;
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
@RequestMapping("/catalog")
public class CatalogResource extends CzBaseResource {

    private final CatalogService catalogService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogResource.class);

    @Autowired
    public CatalogResource(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping("/getProfilesToShow")
    public CzResponse getProfilesToShow(@RequestParam String userId){
        validateNonNullRequestParams(userId);
        LOGGER.info(String.format("Received request to fetch profiles to be shown for user id %s",userId));
        try{
            return new CzResponse(ResponseStatus.SUCCESS,catalogService.getProfilesToShow(userId));
        }catch (Exception e){
            LOGGER.error("Unable to fetch profiles", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping("/getProviderCategoryDetails")
    public CzResponse getProviderCategoryDetails(){
        LOGGER.info("Received request to fetch provider categories");
        try{
            return new CzResponse(ResponseStatus.SUCCESS,catalogService.getProviderCategoryDetails());
        }catch (Exception e){
            LOGGER.error("Unable to fetch provider categories", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping("/getSeekerCategoryDetails")
    public CzResponse getSeekerCategoryDetails(){
        LOGGER.info("Received request to fetch seeker categories");
        try{
            return new CzResponse(ResponseStatus.SUCCESS,catalogService.getSeekerCategoryDetails());
        }catch (Exception e){
            LOGGER.error("Unable to fetch provider categories", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateUserProviderCategories")
    public CzResponse updateUserProviderCategories(@RequestBody CategoryRequest categoryRequest){
        validateRequest(categoryRequest);
        LOGGER.info("Received request to update provider categories");
        try{
            catalogService.updateUserProviderCategories(categoryRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to fetch provider categories", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/updateUserSeekerCategories")
    public CzResponse updateUserSeekerCategories(@RequestBody CategoryRequest categoryRequest){
        validateRequest(categoryRequest);
        LOGGER.info("Received request to update seeker categories");
        try{
            catalogService.updateUserSeekerCategories(categoryRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to update seeker categories", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping("/gotRightSwipes")
    public CzResponse gotRightSwipes(@RequestParam String userId){
        validateNonNullRequestParams(userId);
        LOGGER.info(String.format("Received request to fetch right swipes list for user %s",userId));
        try{
            return new CzResponse(ResponseStatus.SUCCESS,catalogService.gotRightSwipes(userId));
        }catch (Exception e){
            LOGGER.error("Unable to fetch right swipes list", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping("/gotMatches")
    public CzResponse gotMatches(@RequestParam String userId){
        validateNonNullRequestParams(userId);
        LOGGER.info(String.format("Received request to fetch new matches list for user %s",userId));
        try{
            return new CzResponse(ResponseStatus.SUCCESS,catalogService.gotMatches(userId));
        }catch (Exception e){
            LOGGER.error("Unable to fetch new matches list", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/didMatch")
    public CzResponse didMatch(@RequestBody SwipeRequest swipeRequest){
        validateRequest(swipeRequest);
        LOGGER.info("Received request to match another user");
        try{
            catalogService.didMatch(swipeRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to match another user", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/unmatch")
    public CzResponse unmatch(@RequestBody SwipeRequest swipeRequest){
        validateRequest(swipeRequest);
        LOGGER.info("Received request to unmatch another user");
        try{
            catalogService.unmatch(swipeRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to unmatch another user", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/didRightSwipes")
    public CzResponse didRightSwipes(@RequestBody SwipeRequest swipeRequest){
        validateRequest(swipeRequest);
        LOGGER.info("Received request to update right swipes bucket");
        try{
            catalogService.didRightSwipes(swipeRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to update right swipes bucket", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT,value = "/didLeftSwipes")
    public CzResponse didLeftSwipes(@RequestBody SwipeRequest swipeRequest){
        validateRequest(swipeRequest);
        LOGGER.info("Received request to update left swipes bucket");
        try{
            catalogService.didLeftSwipes(swipeRequest);
            return new CzResponse(ResponseStatus.SUCCESS);
        }catch (Exception e){
            LOGGER.error("Unable to update left swipes bucket", e);
            return new CzResponse(ResponseStatus.FAILURE, e.getMessage());
        }

    }







}

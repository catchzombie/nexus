package com.catchzombie;

import com.catchzombie.profile.ProfileDTO;
import com.catchzombie.request.impl.CategoryRequest;
import com.catchzombie.request.impl.SwipeRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by shubham on 5/2/17.
 */
@Service
public class CatalogService {

    public Set<ProfileDTO> getProfilesToShow(String userId) {
        return null;
    }

    public Set<String> getProviderCategoryDetails() {
        return null;
    }

    public Set<String> getSeekerCategoryDetails() {
        return null;
    }

    public void updateUserProviderCategories(CategoryRequest categoryRequest) {

    }

    public void updateUserSeekerCategories(CategoryRequest categoryRequest) {
    }

    public Set<String> gotRightSwipes(String userId) {
        return null;
    }

    public Set<String> gotMatches(String userId) {
        return null;
    }

    public void didMatch(SwipeRequest swipeRequest) {
    }

    public void unmatch(SwipeRequest swipeRequest) {
    }

    public void didRightSwipes(SwipeRequest swipeRequest) {
    }

    public void didLeftSwipes(SwipeRequest swipeRequest) {
    }
}

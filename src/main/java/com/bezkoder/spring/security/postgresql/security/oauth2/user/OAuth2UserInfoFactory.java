package com.bezkoder.spring.security.postgresql.security.oauth2.user;



import com.bezkoder.spring.security.postgresql.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

import static com.bezkoder.spring.security.postgresql.models.enums.AuthProvider.google;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}

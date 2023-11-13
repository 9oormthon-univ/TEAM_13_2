package com.groom.cookiehouse.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2AuthorizationRequest {

    private String referer;
    private String redirectUri;

    @Builder
    public OAuth2AuthorizationRequest(String referer, String redirectUri) {
        this.referer = referer;
        this.redirectUri = redirectUri;
    }
}
package com.groom.cookiehouse.oauth2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ClientRegistration {
    private String registrationId;
    private String clientId;
    private String clientSecret;
    private String authorizationGrantType;
    private String redirectUri;
    private String scope;
    private ProviderDetails providerDetails = new ProviderDetails();

    @Builder
    public ClientRegistration(
            String registrationId,
            String clientId,
            String authorizationGrantType,
            String clientSecret,
            String redirectUri,
            String scope,
            String authorizationUri,
            String tokenUri,
            String userInfoUri
    ) {
        this.registrationId = registrationId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationGrantType = authorizationGrantType;
        this.redirectUri = redirectUri;
        this.scope = scope;
        this.providerDetails.authorizationUri = authorizationUri;
        this.providerDetails.tokenUri = tokenUri;
        this.providerDetails.userInfoUri = userInfoUri;
    }

    @Getter
    @Setter
    public class ProviderDetails {
        private String authorizationUri;
        private String tokenUri;
        private String userInfoUri;
    }
}


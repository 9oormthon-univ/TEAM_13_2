package com.groom.cookiehouse.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class OAuth2AuthorizationResponse {
    private String state;
    private String code;
    private String error;
}
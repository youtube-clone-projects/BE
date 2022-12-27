//package com.sparta.akijaki.oauth;
//
//import lombok.Builder;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//
//
//
//public enum CommonOAuth2Provider {
//    GOOGLE {
//        @Override
//        public Builder getBuilder(String registrationId){
//            ClientRegistration.Builder builder =getBuilder(registrationId,
//                    ClientAuthenticationMethod.BASIC,DEFAULT_REDIRECT_URL);
//
//        builder.scope("openid","profile","email");
//        builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
//        builder.tokenUri("https://www.googleapis.com/oauth/v3/certs");
//        builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/userinfo");
//        builder.userNameAttributeName(IdTokenClaimNames.SUB);
//        builder.clientName("Google");
//        return builder;
//
//        }
//    };
//
//}

package com.groom.cookiehouse.oauth2;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientRegistrationRepository {
    private final Map<String, ClientRegistration> registrations;

    public ClientRegistrationRepository(Map<String, ClientRegistration> registrations) {
        this.registrations = registrations;
    }

    public ClientRegistrationRepository(List<ClientRegistration> registrations) {
        this(createRegistrationsMap(registrations));
    }

    private static Map<String, ClientRegistration> createRegistrationsMap(List<ClientRegistration> registrations) {
        return toUnmodifiableConcurrentMap(registrations);
    }

    // 멀티 스레드 환경에서 안전하게 사용 가능한 ConcurrentHashMap
    // 불변한 Map을 생성하기 위한 Collections.unmodifiable() 메소드 사용
    private static Map<String, ClientRegistration> toUnmodifiableConcurrentMap(List<ClientRegistration> registrations) {
        ConcurrentHashMap<String, ClientRegistration> result = new ConcurrentHashMap<>();
        for (ClientRegistration registration : registrations)
            result.put(registration.getRegistrationId(), registration);
        return Collections.unmodifiableMap(result);
    }

    public ClientRegistration findByRegistrationId(String registrationId) {
        return this.registrations.get(registrationId);
    }
}


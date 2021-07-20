package hu.procats.invoicemanager.models;

import hu.procats.invoicemanager.jpamodels.User;

import java.util.Map;

public class AuthenticationResponse {
    private final String jwt;
    private final Map<String, String> user;

    public AuthenticationResponse(String jwt, Map<String, String> user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public Map<String, String> getUser()
    {
        return user;
    }
}

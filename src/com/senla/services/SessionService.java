package com.senla.services;

import com.senla.entity.Session;

public class SessionService {
    public boolean isAuthenticated(Session session) {
        return session.getCard() != null;
    }
}

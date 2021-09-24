package com.senla.services;

import com.senla.entity.Card;
import com.senla.entity.Session;

public class SessionService {
    public boolean isAuthenticated(Session session) {
        return session.getCard() != null;
    }

    public void closeSession(Session session){
        session.setCard(null);
    }

}

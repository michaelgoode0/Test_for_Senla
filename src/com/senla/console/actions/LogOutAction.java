package com.senla.console.actions;

import com.senla.entity.Session;
import com.senla.services.SessionService;

public class LogOutAction implements IAction{
    private Session session;
    private SessionService service;

    public LogOutAction(Session session, SessionService service) {
        this.session = session;
        this.service = service;
    }

    @Override
    public void execute() throws Exception {
        service.closeSession(session);
        System.out.println("Logging out...");
    }
}

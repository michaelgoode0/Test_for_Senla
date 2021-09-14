package com.senla.console.actions;

import com.senla.entity.Session;

public class LogOutAction implements IAction{
    private Session session;

    public LogOutAction(Session session) {
        this.session = session;
    }

    @Override
    public void execute() throws Exception {
        session.setCard(null);
        System.out.println("Logging out...");
    }
}

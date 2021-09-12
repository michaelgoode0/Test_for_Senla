package com.senla.console.actions;

public class LogOutAction implements IAction{
    private Authorization authorization;

    public LogOutAction(Authorization authorization) {
        this.authorization = authorization;
    }

    @Override
    public void execute() throws Exception {
        authorization.setCard(null);
    }
}

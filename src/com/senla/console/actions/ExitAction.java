package com.senla.console.actions;

public class ExitAction implements IAction {

	@Override
	public void execute() {
		System.out.println("Process terminated");
		System.exit(0);
	}
}

package main.java.by.epam.controller.command;

import main.java.by.epam.controller.exception.ControllerException;

public interface Command {	
	
	String execute (String request) throws ControllerException;

}

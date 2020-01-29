package by.epam.controller.command;

import by.epam.controller.exception.ControllerException;

public interface Command {	
	
	String execute (String request) throws ControllerException;

}

package by.epam.controller.command;

import by.epam.controller.exception.ControllerException;

public interface Command {
	
	char delimiterRequest = ' ';
	
	String execute (String request) throws ControllerException;

}

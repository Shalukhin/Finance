package main.java.by.epam.controller;

import main.java.by.epam.controller.command.Command;
import main.java.by.epam.controller.exception.ControllerException;
import main.java.by.epam.util.Parser;

public class Controller {
	
	private CommandProvider commandProvider = new CommandProvider();

	public String executeTask(String request) throws ControllerException {
		String commandName = request.substring(0, request.indexOf(Parser.DELIMITER));
		Command command = commandProvider.getCommand(commandName);
		String response = command.execute(request);
		
		return response;		
	}
}

package by.epam.controller;

import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;

public class Controller {
	
	private CommandProvider commandProvider = new CommandProvider();
	private char delimiter = ' ';
	
	public String executeTask(String request) throws ControllerException {
		String commandName = request.substring(0, request.indexOf(delimiter));
		Command command = commandProvider.getCommand(commandName);
		String response = command.execute(request);
		
		return response;
		
	}
	
	

}

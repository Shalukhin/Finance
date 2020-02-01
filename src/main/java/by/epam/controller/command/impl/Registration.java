package main.java.by.epam.controller.command.impl;

import main.java.by.epam.controller.command.Command;
import main.java.by.epam.controller.exception.ControllerException;
import main.java.by.epam.service.UserService;
import main.java.by.epam.service.exception.ServiceException;
import main.java.by.epam.service.factory.ServiceFactory;
import main.java.by.epam.util.Parser;

public class Registration implements Command {

	@Override
	public String execute(String request) throws ControllerException {		
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();		
		
		String login = Parser.getValueParam(request, "login");			
		String password = Parser.getValueParam(request, "password");
		
		boolean result = false;
		try {
			result = userService.registration(login, password);
		} catch (ServiceException e) {
			throw new ControllerException("registration_error", e);
		}
		
		return result ? "Поздравляем! Вы зарегистрировались!" : "При регистрации что-то пошло не так. Попробуйте ещё раз!";
	}
}

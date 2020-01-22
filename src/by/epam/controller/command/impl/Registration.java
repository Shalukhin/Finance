package by.epam.controller.command.impl;

import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;
import by.epam.service.UserService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;
import by.epam.util.Parser;

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

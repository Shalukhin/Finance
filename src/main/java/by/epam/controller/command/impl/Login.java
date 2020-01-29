package by.epam.controller.command.impl;

import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;
import by.epam.service.UserService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;
import by.epam.util.Parser;

public class Login implements Command {

	@Override
	public String execute(String request) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();		
		
		String login = Parser.getValueParam(request, "login");			
		String password = Parser.getValueParam(request, "password");
		
		boolean result = false;
		try {
			result = userService.login(login, password);
		} catch (ServiceException e) {
			throw new ControllerException("login_error", e);
		}		
		return (result ? "Приветствуем! Вы вошли!" : "Ошибка при авторизации. Попробуйте ещё раз!");
	}
}

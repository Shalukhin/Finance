package by.epam.controller.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;
import by.epam.service.UserService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;

public class Registration implements Command {

	@Override
	public String execute(String request) throws ControllerException {		
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();		
		
		String login = getValueParam(request, "login");			
		String password = getValueParam(request, "password");
		
		boolean result = false;
		try {
			result = userService.registration(login, password);
		} catch (ServiceException e) {
			throw new ControllerException("registration_error", e);
		}
		
		return result ? "Поздравляем! Вы зарегистрировались!" : "При регистрации что-то пошло не так. Попробуйте ещё раз!";
	}

	private String getValueParam(String request, String nameParam) {
		
		Pattern pat = Pattern.compile(nameParam + "=(\\S+)");
		Matcher mat = pat.matcher(request);
		if (mat.find()) {
			return mat.group(1);
		} else {
			return null;
		}
	}

}

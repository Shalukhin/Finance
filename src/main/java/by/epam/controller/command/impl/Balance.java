package by.epam.controller.command.impl;

import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;
import by.epam.service.ItemService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;
import by.epam.util.Parser;

public class Balance implements Command {

	@Override
	public String execute(String request) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();		
		ItemService itemService = serviceFactory.getItemService();
		
		String loginUser = Parser.getValueParam(request, "login");		
		
		try {
			return String.valueOf((double)(itemService.getSumAllUsersItems(loginUser)) / 100);
		} catch (ServiceException e) {
			throw new ControllerException("balance_error", e);
		}
	}
}

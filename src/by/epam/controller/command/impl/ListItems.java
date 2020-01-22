package by.epam.controller.command.impl;

import java.util.List;
import by.epam.bean.Item;
import by.epam.controller.command.Command;
import by.epam.controller.exception.ControllerException;
import by.epam.service.ItemService;
import by.epam.service.exception.ServiceException;
import by.epam.service.factory.ServiceFactory;
import by.epam.util.Parser;

public class ListItems implements Command {

	@Override
	public String execute(String request) throws ControllerException {
		ServiceFactory serviceFactory = ServiceFactory.getInstance();		
		ItemService itemService = serviceFactory.getItemService();
		
		String loginUser = Parser.getValueParam(request, "login");	
		String itemType = Parser.getValueParam(request, "itemType");
		
		List<Item> items;
		try {
			items = itemService.getAllUsersItems(loginUser, itemType);
		} catch (ServiceException e) {
			throw new ControllerException("list_items_error", e);
		}
		StringBuilder sb = new StringBuilder();
		
		if (items.size() == 0) {
			sb.append("!операций нет!");
			sb.append("\n");
		}
		
		for (Item item : items) {
			sb.append(item.toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}

}

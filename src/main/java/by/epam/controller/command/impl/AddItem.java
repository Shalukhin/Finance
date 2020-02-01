package main.java.by.epam.controller.command.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.by.epam.controller.command.Command;
import main.java.by.epam.controller.exception.ControllerException;
import main.java.by.epam.service.ItemService;
import main.java.by.epam.service.exception.ServiceException;
import main.java.by.epam.service.factory.ServiceFactory;
import main.java.by.epam.util.Parser;

public class AddItem implements Command {

	@Override
	public String execute(String request) throws ControllerException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
		ServiceFactory serviceFactory = ServiceFactory.getInstance();		
		ItemService itemService = serviceFactory.getItemService();
		
		String loginUser = Parser.getValueParam(request, "login");
		long amountCoins = Long.valueOf(Parser.getValueParam(request, "amountCoins"));
		String dateStr = Parser.getValueParam(request, "date");
		Date date;
		if (dateStr.equals("now")) {
			date = new Date();
		} else {
			try {
				date = simpleDateFormat.parse(Parser.getValueParam(request, "date") + "  00:00:00");
			} catch (ParseException e) {
				throw new ControllerException("parse_date_error", e);
			}
		}	
		
		String kind = Parser.getValueParam(request, "kind");
		
		boolean result;
		try {
			result = itemService.addNewItem(loginUser, amountCoins, date, kind);
		} catch (ServiceException e) {
			throw new ControllerException("balance_error", e);
		}
		return (result ? "Статья добавлена!" : "Статья не добавлена! Попробуйте ещё раз!");
	}

}

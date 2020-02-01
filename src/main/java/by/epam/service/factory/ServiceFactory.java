package main.java.by.epam.service.factory;

import main.java.by.epam.service.ItemService;
import main.java.by.epam.service.UserService;
import main.java.by.epam.service.impl.ItemServiceImpl;
import main.java.by.epam.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private ItemService itemService = new ItemServiceImpl();
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public ItemService getItemService() {
		return itemService;
	}
	

}

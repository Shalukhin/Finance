package by.epam.service.factory;

import by.epam.service.ItemService;
import by.epam.service.UserService;
import by.epam.service.impl.ItemServiceImpl;
import by.epam.service.impl.UserServiceImpl;

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

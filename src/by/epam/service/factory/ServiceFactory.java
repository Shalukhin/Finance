package by.epam.service.factory;

import by.epam.service.ExpenseService;
import by.epam.service.UserService;
import by.epam.service.impl.ExpenseServiceImpl;
import by.epam.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	private static ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private ExpenseService expenseService = new ExpenseServiceImpl();
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	public UserService getUserService(){
		return userService;
	}
	
	public ExpenseService getExpenseService() {
		return expenseService;
	}
	

}

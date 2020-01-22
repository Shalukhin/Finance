package by.epam.dao.factory;

import by.epam.dao.ExpenseDAO;
import by.epam.dao.UserDAO;
import by.epam.dao.impl.FileExpenseDAO;
import by.epam.dao.impl.FileUserDAO;

public class DAOFactory {
	
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new FileUserDAO();
	private final ExpenseDAO expenseDAO = new FileExpenseDAO();	
	
	private DAOFactory() {};
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}
}

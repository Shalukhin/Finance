package by.epam.dao.factory;

import by.epam.dao.ItemDAO;
import by.epam.dao.UserDAO;
import by.epam.dao.impl.FileItemDAO;
import by.epam.dao.impl.FileUserDAO;

public class DAOFactory {
	
	private final static DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new FileUserDAO();
	private final ItemDAO itemDAO = new FileItemDAO();	
	
	private DAOFactory() {};
	
	public static DAOFactory getInstance() {
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ItemDAO getItemDAO() {
		return itemDAO;
	}
}

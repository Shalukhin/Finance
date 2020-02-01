package main.java.by.epam.dao.factory;

import main.java.by.epam.dao.ItemDAO;
import main.java.by.epam.dao.UserDAO;
import main.java.by.epam.dao.impl.FileItemDAO;
import main.java.by.epam.dao.impl.FileUserDAO;

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

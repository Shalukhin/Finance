package main.java.by.epam.service;

import main.java.by.epam.service.exception.ServiceException;

public interface UserService {
	
	boolean registration(String login, String password) throws ServiceException;
	boolean login(String login, String password) throws ServiceException;
	void logout();
	

}

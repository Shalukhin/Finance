package by.epam.service;

import by.epam.bean.User;
import by.epam.service.exception.ServiceException;

public interface UserService {
	
	boolean registration(String login, String password) throws ServiceException;
	boolean login(String login, String password);
	void logout();
	

}

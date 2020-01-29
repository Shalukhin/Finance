package by.epam.service.impl;

import java.util.List;
import by.epam.bean.User;
import by.epam.dao.UserDAO;
import by.epam.dao.exception.DAOException;
import by.epam.dao.factory.DAOFactory;
import by.epam.service.UserService;
import by.epam.service.exception.ServiceException;
import by.epam.util.Validator;

public class UserServiceImpl implements UserService {
	
	DAOFactory daoFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoFactory.getUserDAO();

	@Override
	public boolean registration(String login, String password) throws ServiceException {
		if (!Validator.isValidStrValue(login)) {
			throw new ServiceException("invalid_login");
		}
		if (!Validator.isValidStrValue(password)) {
			throw new ServiceException("invalid_password");
		}
		
		List<User> allUsers = null;
		try {
			allUsers = userDAO.read();
		} catch (DAOException e) {
			throw new ServiceException("List_users_error", e);
		}
		for (User user : allUsers) {
			if (user.getLogin().equals(login)) {
				return false;
			}
		}
		int id;
		if(allUsers.size() > 0) {
			id = allUsers.get(allUsers.size() - 1).getId() + 1;
		} else {
			id = 0;
		}
		
		
		String role = "user";
		
		User user = new User(id, login, password, role);
		
		try {
			return userDAO.create(user);
		} catch (DAOException e) {
			throw new ServiceException("Add_user_error", e);
		}
	}

	@Override
	public boolean login(String login, String password) throws ServiceException {
		if (!Validator.isValidStrValue(login)) {			
			throw new ServiceException("invalid_login");
		}
		if (!Validator.isValidStrValue(password)) {			
			throw new ServiceException("invalid_password");
		}
		List<User> allUsers = null;
		try {
			allUsers = userDAO.read();
		} catch (DAOException e) {
			throw new ServiceException("List_users_error", e);
		}
		for (User user : allUsers) {			
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
	

}

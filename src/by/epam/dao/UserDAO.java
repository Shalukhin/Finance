package by.epam.dao;

import by.epam.bean.User;
import by.epam.dao.exception.DAOException;

public interface UserDAO extends DAO<User>{	
	
	User read(String login) throws DAOException;
	

}

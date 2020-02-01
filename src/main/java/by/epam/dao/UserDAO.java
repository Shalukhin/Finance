package main.java.by.epam.dao;

import main.java.by.epam.bean.User;
import main.java.by.epam.dao.exception.DAOException;

public interface UserDAO extends DAO<User>{	
	
	User read(String login) throws DAOException;
	

}

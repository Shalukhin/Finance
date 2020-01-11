package by.epam.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import by.epam.bean.User;
import by.epam.dao.UserDAO;
import by.epam.dao.exception.DAOException;
import by.epam.util.ConstantsDAO;
import by.epam.util.DriversFileDAO;
import by.epam.util.exception.InvalidValueException;

public class FileUserDAO implements UserDAO {

	@Override
	public boolean create(User user) throws DAOException {
		if (user == null) {
			throw new DAOException("user_null");
		}

		String userStr = buildUserStr(user);

		try {
			return DriversFileDAO.writeToEndOfFile(ConstantsDAO.LINK_USERS, userStr);
		} catch (IOException e) {
			throw new DAOException("user_create_error", e);
		}
	}	

	@Override
	public User read(int id) throws DAOException {

		ArrayList<User> usersArr = buildArrayUsersFromFile();

		for (User user : usersArr) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new DAOException("user_not_found");
	}

	@Override
	public User read(String login) throws DAOException {

		ArrayList<User> usersArr = buildArrayUsersFromFile();

		for (User user : usersArr) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		throw new DAOException("user_not_found");
	}

	@Override
	public ArrayList<User> read() throws DAOException {
		return buildArrayUsersFromFile();
	}

	@Override
	public boolean update(User userModified) throws DAOException {

		if (userModified == null) {
			throw new DAOException("user_null");
		}

		ArrayList<User> usersArr = buildArrayUsersFromFile();

		for (int i = 0; i < usersArr.size(); i++) {
			if (usersArr.get(i).getId() == userModified.getId()) {
				if (usersArr.get(i).equals(userModified)) {
					return false;
				} else {
					usersArr.set(i, userModified);
					break;
				}
			}

			if (i == usersArr.size() - 1) {
				throw new DAOException("user_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_USERS);
		} catch (IOException e) {
			throw new DAOException("clean_user_error", e);
		}

		for (User user : usersArr) {
			create(user);
		}

		return true;
	}

	@Override
	public boolean delete(int id) throws DAOException {
		ArrayList<User> usersArr = buildArrayUsersFromFile();

		for (int i = 0; i < usersArr.size(); i++) {
			if (usersArr.get(i).getId() == id) {
				usersArr.remove(i);
			}

			if (i == usersArr.size() - 1) {
				throw new DAOException("user_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_USERS);
		} catch (IOException e) {
			throw new DAOException("clean_user_error", e);
		}

		for (User user : usersArr) {
			create(user);
		}

		return true;

	}

	@Override
	public boolean delete(User user) throws DAOException {
		
		if (user == null) {
			throw new DAOException("user_null");
		}
		
		return delete(user.getId());
	}	

	@Override
	public void deleteAll() throws DAOException {
		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_USERS);
		} catch (IOException e) {
			throw new DAOException("clean_user_error", e);
		}
	}
	
	private String buildUserStr(User user) {

		StringBuilder userStr = new StringBuilder();
		userStr.append("<user>\n\t<id>");
		userStr.append(user.getId());
		userStr.append("</id>\n\t<login>");
		userStr.append(user.getLogin());
		userStr.append("</login>\n\t<password>");
		userStr.append(user.getPassword());
		userStr.append("</password>\n\t<role>");
		userStr.append(user.getRole());
		userStr.append("</role>\n</user>");

		return userStr.toString();
	}
	
	private ArrayList<User> buildArrayUsersFromFile() throws DAOException {
		ArrayList<User> usersArr = new ArrayList<>();
		
		String usersBaseStr = null;
		try {
			usersBaseStr = DriversFileDAO.extractTextFromFile(ConstantsDAO.LINK_USERS);
		} catch (IOException e) {
			throw new DAOException("user_read_error", e);
		}	
		
		ArrayList<String> usersArrStr = DriversFileDAO.getArrayTagsFromTextByName(usersBaseStr, "user");
		for (String userStr : usersArrStr) {
			User user = new User();
			user.setId(Integer.valueOf(DriversFileDAO.getArrayTagsFromTextByName(userStr, "id").get(0)));
			try {
				user.setLogin(DriversFileDAO.getArrayTagsFromTextByName(userStr, "login").get(0));
				user.setPassword(DriversFileDAO.getArrayTagsFromTextByName(userStr, "password").get(0));
				user.setRole(DriversFileDAO.getArrayTagsFromTextByName(userStr, "role").get(0));
			} catch (InvalidValueException e) {
				throw new DAOException("user_read_error", e);
			}

			usersArr.add(user);
		}
		return usersArr;
	}
	
}

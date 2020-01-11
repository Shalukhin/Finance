package by.epam.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import by.epam.bean.User;
import by.epam.dao.exception.DAOException;
import by.epam.dao.impl.FileUserDAO;
import by.epam.util.exception.InvalidValueException;

public class Runner {

	public static void main(String[] args) throws DAOException, InvalidValueException {

		FileUserDAO test = new FileUserDAO();

		ArrayList<User> u = test.read();

		for (User uu : u) {
			System.out.println(uu);
		}

		User user = new User(4, "jena", "555", "user");

		test.create(user);

		u = test.read();

		for (User uu : u) {
			System.out.println(uu);
		}

	}

}

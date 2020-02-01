package main.java.by.epam.util;

public class ConstantsDAO {

	public static final String LINK_USERS;

	static {
		StringBuilder link = new StringBuilder();
		link.append(System.getProperty("user.dir"));
		link.append("\\src\\main\\resources\\data\\users.txt");
		LINK_USERS = link.toString();
	}

	public static final String LINK_ITEMS;

	static {
		StringBuilder link = new StringBuilder();
		link.append(System.getProperty("user.dir"));
		link.append("\\src\\main\\resources\\data\\items.txt");
		LINK_ITEMS = link.toString();
	}
}

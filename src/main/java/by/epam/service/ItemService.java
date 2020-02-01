package main.java.by.epam.service;

import java.util.Date;
import java.util.List;

import main.java.by.epam.bean.Item;
import main.java.by.epam.bean.User;
import main.java.by.epam.service.exception.ServiceException;

public interface ItemService {
	
	boolean addNewItem(String login, long amountCoins, Date date, String kind) throws ServiceException;
	List<Item> getAllUsersItems(String login, String itemType) throws ServiceException;
	List<Item> getAllUsersItemsKind(User user, String kind);
	List<Item> getAllUsersItemsInPeriod(User user, Date startPeriod, Date EndPeriod);
	List<Item> getAllUsersItemsKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod);
	long getSumAllUsersItems(String login) throws ServiceException;
	long getSumPositiveOrNegativeUsersItems(String login, String itemType) throws ServiceException;
	long getSumAllUsersItemsKind(User user, String kind);
	long getSumAllUsersItemsInPeriod(User user, Date startPeriod, Date EndPeriod);
	long getSumAllUsersItemsKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod);
}

package by.epam.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.epam.bean.Item;
import by.epam.bean.User;
import by.epam.dao.ItemDAO;
import by.epam.dao.UserDAO;
import by.epam.dao.exception.DAOException;
import by.epam.dao.factory.DAOFactory;
import by.epam.service.ItemService;
import by.epam.service.exception.ServiceException;

public class ItemServiceImpl implements ItemService {

	DAOFactory daoFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoFactory.getUserDAO();
	ItemDAO itemDAO = daoFactory.getItemDAO();

	@Override
	public boolean addNewItem(String login, long amountCoins, Date date, String kind) throws ServiceException {
		if (login == null || date == null || kind == null) {
			throw new ServiceException("add_item_parametrs_null");
		}

		int idUser;
		try {
			idUser = userDAO.read(login).getId();
		} catch (DAOException e) {
			throw new ServiceException("read_user_error");
		}

		int id;
		try {
			List<Item> items = itemDAO.read();
			if (items.size() > 0) {
				id = items.get(items.size() - 1).getId() + 1;
			} else {
				id = 0;
			}
		} catch (DAOException e) {
			throw new ServiceException("read_items_error");
		}

		Item itemAdd = new Item(id, idUser, date, amountCoins, kind);

		try {
			return itemDAO.create(itemAdd);
		} catch (DAOException e) {
			throw new ServiceException("add_item_eror", e);
		}
	}

	@Override
	public List<Item> getAllUsersItems(String login, String itemType) throws ServiceException {
		if (login == null || itemType == null) {
			throw new ServiceException("get_all_items_parameters_null");
		}
		
		int userId;
		try {
			userId = userDAO.read(login).getId();
		} catch (DAOException e) {
			throw new ServiceException("read_user_error");
		}
		List<Item> items = null;
		try {
			items = itemDAO.read();
		} catch (DAOException e) {
			throw new ServiceException("read_items_error");
		}
		
		List<Item> result = new ArrayList<>();
		
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getIdUser() == userId) {
				result.add(items.get(i));
			}
		}
		
		if (itemType.equals("all")) {
			return result;
		}
		
		items.clear();
		items.addAll(result);
		result.clear();
		
		for (int i = 0; i < items.size(); i++) {
			if (itemType.equals("expense") ? (items.get(i).getAmountCoins() < 0) : (items.get(i).getAmountCoins() >= 0)) {
				result.add(items.get(i));
			}
		}
				
		return result;
	}

	@Override
	public List<Item> getAllUsersItemsKind(User user, String kind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllUsersItemsInPeriod(User user, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getAllUsersItemsKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSumAllUsersItems(String login) throws ServiceException {

		if (login == null) {
			throw new ServiceException("login_null");
		}

		long sum = 0;

		int userId;
		try {
			userId = userDAO.read(login).getId();
		} catch (DAOException e) {
			throw new ServiceException("read_user_error");
		}
		List<Item> items = null;
		try {
			items = itemDAO.read();
		} catch (DAOException e) {
			throw new ServiceException("read_items_error");
		}
		for (Item item : items) {
			if (item.getIdUser() == userId) {
				sum = sum + item.getAmountCoins();
			}
		}
		return sum;
	}

	@Override
	public long getSumPositiveOrNegativeUsersItems(String login, String itemType) throws ServiceException {
		if (login == null || itemType == null) {
			throw new ServiceException("get_sum_parameters_null");
		}

		long sum = 0;

		int userId;
		try {
			userId = userDAO.read(login).getId();
		} catch (DAOException e) {
			throw new ServiceException("read_user_error");
		}
		List<Item> items = null;
		try {
			items = itemDAO.read();
		} catch (DAOException e) {
			throw new ServiceException("read_items_error");
		}
		for (Item item : items) {
			if (item.getIdUser() == userId && (itemType.equals("expense") ? (item.getAmountCoins() < 0) : (item.getAmountCoins() >= 0))) {
				sum = sum + item.getAmountCoins();
			}
		}
		return sum;
	}

	@Override
	public long getSumAllUsersItemsKind(User user, String kind) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSumAllUsersItemsInPeriod(User user, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSumAllUsersItemsKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return 0;
	}

}

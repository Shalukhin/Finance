package by.epam.dao.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import by.epam.bean.Item;
import by.epam.dao.ItemDAO;
import by.epam.dao.exception.DAOException;
import by.epam.util.ConstantsDAO;
import by.epam.util.DriversFileDAO;

public class FileItemDAO implements ItemDAO{

	@Override
	public boolean create(Item item) throws DAOException {
		if (item == null) {
			throw new DAOException("item_null");
		}

		String itemStr = buildItemStr(item);

		try {
			return DriversFileDAO.writeToEndOfFile(ConstantsDAO.LINK_ITEMS, itemStr);
		} catch (IOException e) {
			throw new DAOException("item_create_error", e);
		}
	}

	@Override
	public Item read(int id) throws DAOException {
		ArrayList<Item> itemArr = buildArrayItemFromFile();

		for (Item expense : itemArr) {
			if (expense.getId() == id) {
				return expense;
			}
		}
		throw new DAOException("expense_not_found");
	}

	@Override
	public ArrayList<Item> read() throws DAOException {
		return buildArrayItemFromFile();
	}

	@Override
	public boolean update(Item itemModified) throws DAOException {

		if (itemModified == null) {
			throw new DAOException("expense_null");
		}

		ArrayList<Item> itemsArr = buildArrayItemFromFile();

		for (int i = 0; i < itemsArr.size(); i++) {
			if (itemsArr.get(i).getId() == itemModified.getId()) {
				if (itemsArr.get(i).equals(itemModified)) {
					return false;
				} else {
					itemsArr.set(i, itemModified);
					break;
				}
			}

			if (i == itemsArr.size() - 1) {
				throw new DAOException("item_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_ITEMS);
		} catch (IOException e) {
			throw new DAOException("clean_item_error", e);
		}

		for (Item item : itemsArr) {
			create(item);
		}

		return true;
	}	

	@Override
	public boolean delete(int id) throws DAOException {
		ArrayList<Item> itemsArr = buildArrayItemFromFile();

		for (int i = 0; i < itemsArr.size(); i++) {
			if (itemsArr.get(i).getId() == id) {
				itemsArr.remove(i);
			}

			if (i == itemsArr.size() - 1) {
				throw new DAOException("item_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_ITEMS);
		} catch (IOException e) {
			throw new DAOException("clean_item_error", e);
		}

		for (Item item : itemsArr) {
			create(item);
		}

		return true;
	}
	
	@Override
	public boolean delete(Item item) throws DAOException {
		if (item == null) {
			throw new DAOException("item_null");
		}
		
		return delete(item.getId());
	}		

	@Override
	public void deleteAll() throws DAOException {
		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_ITEMS);
		} catch (IOException e) {
			throw new DAOException("clean_item_error", e);
		}
		
	}
	
	private String buildItemStr(Item item) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");

		StringBuilder itemStr = new StringBuilder();
		itemStr.append("<item>\n\t<id>");
		itemStr.append(item.getId());
		itemStr.append("</id>\n\t<idUser>");
		itemStr.append(item.getIdUser());
		itemStr.append("</idUser>\n\t<date>");
		itemStr.append(simpleDateFormat.format(item.getDate()));
		itemStr.append("</date>\n\t<amountCoins>");
		itemStr.append(item.getAmountCoins());
		itemStr.append("</amountCoins>\n\t<kind>");
		itemStr.append(item.getKind());
		itemStr.append("</kind>\n</item>");

		return itemStr.toString();
	}
	
	private ArrayList<Item> buildArrayItemFromFile() throws DAOException {
		ArrayList<Item> itemsArr = new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
		
		String itemsBaseStr = null;
		try {
			itemsBaseStr = DriversFileDAO.extractTextFromFile(ConstantsDAO.LINK_ITEMS);
		} catch (IOException e) {
			throw new DAOException("item_read_error", e);
		}	
		
		ArrayList<String> itemsArrStr = DriversFileDAO.getArrayTagsFromTextByName(itemsBaseStr, "item");
		for (String itemStr : itemsArrStr) {
			Item item = new Item();
			
			try {
				
				ArrayList<String> temp = DriversFileDAO.getArrayTagsFromTextByName(itemStr, "id");
				if(temp.size() > 0) {
					item.setId(Integer.valueOf(temp.get(0)));
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(itemStr, "idUser");
				if(temp.size() > 0) {
					item.setIdUser(Integer.valueOf(temp.get(0)));
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(itemStr, "date");
				if(temp.size() > 0) {
					item.setDate(simpleDateFormat.parse(temp.get(0)));					
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(itemStr, "amountCoins");
				if(temp.size() > 0) {
					item.setAmountCoins(Long.valueOf(temp.get(0)));					
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(itemStr, "kind");
				if(temp.size() > 0) {
					item.setKind(temp.get(0));					
				}
				
			} catch (Exception e) {
				throw new DAOException("item_read_error", e);
			}

			itemsArr.add(item);
		}
		return itemsArr;
	}

}

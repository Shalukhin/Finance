package by.epam.dao.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import by.epam.bean.Expense;
import by.epam.dao.ExpenseDAO;
import by.epam.dao.exception.DAOException;
import by.epam.util.ConstantsDAO;
import by.epam.util.DriversFileDAO;

public class FileExpenseDAO implements ExpenseDAO{

	@Override
	public boolean create(Expense expense) throws DAOException {
		if (expense == null) {
			throw new DAOException("expense_null");
		}

		String expenseStr = buildExpenseStr(expense);

		try {
			return DriversFileDAO.writeToEndOfFile(ConstantsDAO.LINK_EXPENSES, expenseStr);
		} catch (IOException e) {
			throw new DAOException("expense_create_error", e);
		}
	}

	@Override
	public Expense read(int id) throws DAOException {
		ArrayList<Expense> expensesArr = buildArrayExpensesFromFile();

		for (Expense expense : expensesArr) {
			if (expense.getId() == id) {
				return expense;
			}
		}
		throw new DAOException("expense_not_found");
	}

	@Override
	public ArrayList<Expense> read() throws DAOException {
		return buildArrayExpensesFromFile();
	}

	@Override
	public boolean update(Expense expenseModified) throws DAOException {

		if (expenseModified == null) {
			throw new DAOException("expense_null");
		}

		ArrayList<Expense> expensesArr = buildArrayExpensesFromFile();

		for (int i = 0; i < expensesArr.size(); i++) {
			if (expensesArr.get(i).getId() == expenseModified.getId()) {
				if (expensesArr.get(i).equals(expenseModified)) {
					return false;
				} else {
					expensesArr.set(i, expenseModified);
					break;
				}
			}

			if (i == expensesArr.size() - 1) {
				throw new DAOException("expense_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_EXPENSES);
		} catch (IOException e) {
			throw new DAOException("clean_expense_error", e);
		}

		for (Expense expense : expensesArr) {
			create(expense);
		}

		return true;
	}	

	@Override
	public boolean delete(int id) throws DAOException {
		ArrayList<Expense> expensesArr = buildArrayExpensesFromFile();

		for (int i = 0; i < expensesArr.size(); i++) {
			if (expensesArr.get(i).getId() == id) {
				expensesArr.remove(i);
			}

			if (i == expensesArr.size() - 1) {
				throw new DAOException("expense_not_found");
			}
		}

		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_EXPENSES);
		} catch (IOException e) {
			throw new DAOException("clean_expense_error", e);
		}

		for (Expense expense : expensesArr) {
			create(expense);
		}

		return true;
	}
	
	@Override
	public boolean delete(Expense expense) throws DAOException {
		if (expense == null) {
			throw new DAOException("expense_null");
		}
		
		return delete(expense.getId());
	}		

	@Override
	public void deleteAll() throws DAOException {
		try {
			DriversFileDAO.cleanFile(ConstantsDAO.LINK_EXPENSES);
		} catch (IOException e) {
			throw new DAOException("clean_expense_error", e);
		}
		
	}
	
	private String buildExpenseStr(Expense expense) {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");

		StringBuilder expenseStr = new StringBuilder();
		expenseStr.append("<expense>\n\t<id>");
		expenseStr.append(expense.getId());
		expenseStr.append("</id>\n\t<idUser>");
		expenseStr.append(expense.getIdUser());
		expenseStr.append("</idUser>\n\t<date>");
		expenseStr.append(simpleDateFormat.format(expense.getDate()));
		expenseStr.append("</date>\n\t<amountCoins>");
		expenseStr.append(expense.getAmountCoins());
		expenseStr.append("</amountCoins>\n\t<kind>");
		expenseStr.append(expense.getKind());
		expenseStr.append("</kind>\n</expense>");

		return expenseStr.toString();
	}
	
	private ArrayList<Expense> buildArrayExpensesFromFile() throws DAOException {
		ArrayList<Expense> expensesArr = new ArrayList<>();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
		
		String expensesBaseStr = null;
		try {
			expensesBaseStr = DriversFileDAO.extractTextFromFile(ConstantsDAO.LINK_EXPENSES);
		} catch (IOException e) {
			throw new DAOException("expense_read_error", e);
		}	
		
		ArrayList<String> expensesArrStr = DriversFileDAO.getArrayTagsFromTextByName(expensesBaseStr, "expense");
		for (String expenseStr : expensesArrStr) {
			Expense expense = new Expense();
			
			try {
				
				ArrayList<String> temp = DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "id");
				if(temp.size() > 0) {
					expense.setId(Integer.valueOf(temp.get(0)));
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "idUser");
				if(temp.size() > 0) {
					expense.setIdUser(Integer.valueOf(temp.get(0)));
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "date");
				if(temp.size() > 0) {
					expense.setDate(simpleDateFormat.parse(temp.get(0)));					
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "amountCoins");
				if(temp.size() > 0) {
					expense.setAmountCoins(Long.valueOf(temp.get(0)));					
				}
				
				temp = DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "kind");
				if(temp.size() > 0) {
					expense.setKind(temp.get(0));					
				}
				
			} catch (Exception e) {
				throw new DAOException("expense_read_error", e);
			}

			expensesArr.add(expense);
		}
		return expensesArr;
	}

}

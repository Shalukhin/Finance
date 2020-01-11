package by.epam.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import by.epam.bean.Expense;
import by.epam.dao.ExpenseDAO;
import by.epam.dao.exception.DAOException;
import by.epam.util.ConstantsDAO;
import by.epam.util.DriversFileDAO;
import by.epam.util.exception.InvalidValueException;

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
	public List<Expense> read() throws DAOException {
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

		StringBuilder expenseStr = new StringBuilder();
		expenseStr.append("<expense>\n\t<id>");
		expenseStr.append(expense.getId());
		expenseStr.append("</id>\n\t<idUser>");
		expenseStr.append(expense.getIdUser());
		expenseStr.append("</idUser>\n\t<date>");
		expenseStr.append(expense.getDate().toString());
		expenseStr.append("</date>\n\t<amountCoins>");
		expenseStr.append(expense.getAmountCoins());
		expenseStr.append("</amountCoins>\n\t<kind>");
		expenseStr.append(expense.getKind());
		expenseStr.append("</kind>\n</expense>");

		return expenseStr.toString();
	}
	
	private ArrayList<Expense> buildArrayExpensesFromFile() throws DAOException {
		ArrayList<Expense> expensesArr = new ArrayList<>();
		
		String expensesBaseStr = null;
		try {
			expensesBaseStr = DriversFileDAO.extractTextFromFile(ConstantsDAO.LINK_EXPENSES);
		} catch (IOException e) {
			throw new DAOException("expense_read_error", e);
		}	
		
		ArrayList<String> expensesArrStr = DriversFileDAO.getArrayTagsFromTextByName(expensesBaseStr, "expense");
		for (String expenseStr : expensesArrStr) {
			Expense expense = new Expense();
			expense.setId(Integer.valueOf(DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "id").get(0)));
			expense.setIdUser(Integer.valueOf(DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "idUser").get(0)));
			
			try {
				expense.setDate(new Date(DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "login").get(0)));
				expense.setAmountCoins(Long.valueOf(DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "amountCoins").get(0)));
				expense.setKind(DriversFileDAO.getArrayTagsFromTextByName(expenseStr, "kind").get(0));
			} catch (InvalidValueException e) {
				throw new DAOException("expense_read_error", e);
			}

			expensesArr.add(expense);
		}
		return expensesArr;
	}

}

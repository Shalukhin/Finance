package by.epam.service.impl;

import java.util.ArrayList;
import java.util.Date;

import by.epam.bean.Expense;
import by.epam.bean.User;
import by.epam.service.ExpenseService;

public class ExpenseServiceImpl implements ExpenseService {

	@Override
	public ArrayList<Expense> getAllUsersExpenses(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getAllUsersExpensesKind(User user, String kind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getAllUsersExpensesInPeriod(User user, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getAllUsersExpensesKindInPeriod(User user, String kind, Date startPeriod,
			Date EndPeriod) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSumAllUsersExpenses(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSumAllUsersExpensesKind(User user, String kind) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSumAllUsersExpensesInPeriod(User user, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSumAllUsersExpensesKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

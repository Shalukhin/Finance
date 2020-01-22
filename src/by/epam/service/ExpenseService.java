package by.epam.service;

import java.util.ArrayList;
import java.util.Date;

import by.epam.bean.Expense;
import by.epam.bean.User;

public interface ExpenseService {
	
	ArrayList<Expense> getAllUsersExpenses(User user);
	ArrayList<Expense> getAllUsersExpensesKind(User user, String kind);
	ArrayList<Expense> getAllUsersExpensesInPeriod(User user, Date startPeriod, Date EndPeriod);
	ArrayList<Expense> getAllUsersExpensesKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod);
	long getSumAllUsersExpenses(User user);
	long getSumAllUsersExpensesKind(User user, String kind);
	long getSumAllUsersExpensesInPeriod(User user, Date startPeriod, Date EndPeriod);
	long getSumAllUsersExpensesKindInPeriod(User user, String kind, Date startPeriod, Date EndPeriod);
	
	
	
	

}

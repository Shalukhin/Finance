package by.epam.controller;

import java.util.HashMap;
import java.util.Map;

import by.epam.controller.command.Command;
import by.epam.controller.command.CommandName;
import by.epam.controller.command.impl.Balance;
import by.epam.controller.command.impl.ListExpense;
import by.epam.controller.command.impl.Login;
import by.epam.controller.command.impl.Logout;
import by.epam.controller.command.impl.ProcessError;
import by.epam.controller.command.impl.Registration;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.LOGIN, new Login());
		repository.put(CommandName.LOGOUT, new Logout());
		repository.put(CommandName.BALANCE, new Balance());
		repository.put(CommandName.LIST_EXPENSE, new ListExpense());
		repository.put(CommandName.PROCESS_ERROR, new ProcessError());
	}

	public Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (Exception e) {
			command = repository.get(CommandName.PROCESS_ERROR);
		}
		return command;
	}

}

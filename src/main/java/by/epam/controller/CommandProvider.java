package main.java.by.epam.controller;

import java.util.HashMap;
import java.util.Map;

import main.java.by.epam.controller.command.Command;
import main.java.by.epam.controller.command.CommandName;
import main.java.by.epam.controller.command.impl.AddItem;
import main.java.by.epam.controller.command.impl.Balance;
import main.java.by.epam.controller.command.impl.ListItems;
import main.java.by.epam.controller.command.impl.Login;
import main.java.by.epam.controller.command.impl.Logout;
import main.java.by.epam.controller.command.impl.ProcessError;
import main.java.by.epam.controller.command.impl.Registration;
import main.java.by.epam.controller.command.impl.SumItems;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.LOGIN, new Login());
		repository.put(CommandName.LOGOUT, new Logout());
		repository.put(CommandName.ADD_ITEM, new AddItem());
		repository.put(CommandName.BALANCE, new Balance());
		repository.put(CommandName.SUM_ITEMS, new SumItems());
		repository.put(CommandName.LIST_ITEMS, new ListItems());
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

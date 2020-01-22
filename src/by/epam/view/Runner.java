package by.epam.view;


import by.epam.bean.Expense;
import by.epam.bean.User;
import by.epam.controller.Controller;
import by.epam.controller.exception.ControllerException;
import by.epam.util.ScannersUtils;
import by.epam.util.exception.InvalidValueException;

public class Runner {

	public static void main(String[] args) throws InvalidValueException {
		
		Controller controller = new Controller();

		User user = null;

		int viewCommand;

		do {
			System.out.println("Выберите дейчтвие:\n1) Регистрация\n2) Вход\n\n0) Выход из программы");

			viewCommand = ScannersUtils.getIntFromConsol();

			switch (viewCommand) {
			case (1):
				System.out.println("\tВведите логин:");
				String login = ScannersUtils.getStrFromConsol();
				System.out.println("\tВведите пароль:");
				String password = ScannersUtils.getStrFromConsol();
				
				String request = "registration login=" + login + " password=" + password;
				try {
					System.out.println(controller.executeTask(request));
				} catch (ControllerException e) {
					System.out.println("При регистрации что-то пошло не так. Попробуйте ещё раз!");
				}
				System.out.println();
				break;
			case (2):
				System.out.println("Вход");
				break;
			}

		} while (viewCommand != 0);

	}

}

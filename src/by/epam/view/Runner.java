package by.epam.view;

import by.epam.controller.Controller;
import by.epam.controller.exception.ControllerException;
import by.epam.util.ScannersUtils;
import by.epam.util.exception.InvalidValueException;

public class Runner {

	public static void main(String[] args) throws InvalidValueException {

		Controller controller = new Controller();

		String userLogin = null;

		int viewCommand;

		do {
			if (userLogin == null) {
				System.out.println("Выберите дейчтвие:\n1) Регистрация\n2) Вход\n\n0) Выход из программы");
			} else {
				System.out.println(
						"Выберите дейчтвие:\n1) Регистрация\n2) Выход\n3) Добавить статью расхода/дохода\n4) Посмотреть текущую сумму баланса\n5) "
								+ "Посмотреть сумму расхода/дохода\n6) Просмотреть детализацию\n\n0) Выход из программы");
			}

			viewCommand = ScannersUtils.getIntFromConsol();

			if (userLogin == null && !(viewCommand == 1 || viewCommand == 2 || viewCommand == 0)) {
				continue;
			}

			switch (viewCommand) {
			case (1):
				System.out.println("Для регистрации введите логин и пароль:");
				System.out.println("\tВведите логин:");
				String loginReg = ScannersUtils.getStrFromConsol();
				System.out.println("\tВведите пароль:");
				String passwordReg = ScannersUtils.getStrFromConsol();

				String requestReg = "registration login=" + loginReg + " password=" + passwordReg;
				try {
					System.out.println(controller.executeTask(requestReg));
				} catch (ControllerException e) {
					System.out.println("При регистрации что-то пошло не так. Попробуйте ещё раз!");
				}
				System.out.println();
				break;
			case (2):
				if (userLogin == null) {
					System.out.println("Для авторизации введите логин и пароль:");
					System.out.println("\tВведите логин:");
					String loginIn = ScannersUtils.getStrFromConsol();
					System.out.println("\tВведите пароль:");
					String passwordIn = ScannersUtils.getStrFromConsol();

					String requestIn = "login login=" + loginIn + " password=" + passwordIn;
					String responseIn;
					try {
						responseIn = controller.executeTask(requestIn);
					} catch (ControllerException e) {
						e.printStackTrace();
						responseIn = "При регистрации что-то пошло не так. Попробуйте ещё раз!";
					}
					System.out.println(responseIn);
					System.out.println();

					if (responseIn.equals("Приветствуем! Вы вошли!")) {
						userLogin = loginIn;
					}

				} else {
					System.out.println("До свидания! Вы вышли!");
					userLogin = null;
				}
				break;
			case (3):
				System.out.println("Выберете статью , которую хотите добавить:\n\t1) Расход\n\t2) Доход");
				int commandAddItem = ScannersUtils.getIntFromConsol();
				System.out.println("\tЗаполните данные:");
				System.out.println("\tВведите сумму в руб:");
				double sumAddItem = ScannersUtils.getDoubleFromConsol();
				System.out.println("\tВведите дату \"ГГГГ.ММ.ДД\" либо \"now\" для текущей даты");
				String dateAddItem = ScannersUtils.getStrFromConsol();
				System.out.println("\tВведите категорию:");
				String kindAddItem = ScannersUtils.getStrFromConsol();

				String requestAddItem = "add_item login=" + userLogin + " amountCoins="
						+ (long) (commandAddItem == 1 ? -sumAddItem * 100 : sumAddItem * 100) + " date=" + dateAddItem
						+ " kind=" + kindAddItem;
				String response;

				try {
					response = controller.executeTask(requestAddItem);
				} catch (ControllerException e1) {
					e1.printStackTrace();
					response = "Статья не добавлена! Попробуйте ещё раз!";
				}

				System.out.println(response);
				System.out.println();

				break;
			case (4):

				String requestBalance = "balance login=" + userLogin;
				String responseBalance;
				try {
					responseBalance = controller.executeTask(requestBalance);
				} catch (ControllerException e) {
					responseBalance = "Баланс не доступен, попробуйте позже";
				}
				System.out.println("Ваш баланс равен:\n" + responseBalance + " рублей");
				System.out.println();
				break;
			case (5):
				System.out.println("Выберете сумму, которую хотите посмотреть:\n\t1) Расход\n\t2) Доход");
				int commandSumItem = ScannersUtils.getIntFromConsol();
				String requestSumItem = "sum_items login=" + userLogin + " itemType="
						+ (commandSumItem == 1 ? "expense" : "income");
				String responseSumItem;
				try {
					responseSumItem = controller.executeTask(requestSumItem);
				} catch (ControllerException e) {
					responseSumItem = "Сумма " + (commandSumItem == 1 ? "расходов" : "доходов")
							+ " не доступна, попробуйте позже";
				}

				System.out.println("Сумма " + (commandSumItem == 1 ? "расходов" : "доходов") + " равена:\n"	+ responseSumItem + " рублей");
				System.out.println();

				break;
			case (6):
				System.out.println("Выберете детализацию, которую хотите посмотреть:\n\t1) Всё\n\t2) Расход\n\t3) Доход");
				int commandDetal = ScannersUtils.getIntFromConsol();
				String itemType = "all";
				String itemTypeOnView = "всем";
				switch (commandDetal) {
				case (2):
					itemType = "expense";
					itemTypeOnView = "расходным";
					break;
				case (3):					
					itemType = "income";
					itemTypeOnView = "приходным";
					break;
				}

				String requestDetal = "list_items login=" + userLogin + " itemType=" + itemType;
				String responseDetal;
				try {
					responseDetal = controller.executeTask(requestDetal);
				} catch (ControllerException e) {
					responseDetal = "Детализация временно не доступна! Попробуйте позже!";
				}
				
				System.out.println("Детализация по " + itemTypeOnView + " операциям:");
				System.out.println(responseDetal);

				break;				
			}

		} while (viewCommand != 0);
	}
}

package by.epam.util;

public class Validator {
	
	public static boolean isValidStrValue(String value) {
		if (value == null) {
			return false;
		}
		return value.matches("[a-zA-Z0-9а-яА-ЯёЁ_-]+");
	}
	
	public static boolean isPositive(long value) {
		if (value < 0) {
			return false;
		} else {
			return true;
		}
	}

}

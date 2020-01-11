package by.epam.bean;

import by.epam.util.Validator;
import by.epam.util.exception.InvalidValueException;

public class User {
	
	private int id;
	private String login;
	private String password;
	private String role;
	
	
	public User() {
		super();
		id = 0;
		login = "user0";
		password = "pass0";
		role = "user";
	}
	
	
	public User(int id, String login, String password, String role) throws InvalidValueException {
		super();
		
		validateText(login, "user_login");
		validateText(password, "user_passord");
		validateText(role, "user_role");
		
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) throws InvalidValueException {
		validateText(login, "user_login");
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws InvalidValueException {
		validateText(password, "user_passord");
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) throws InvalidValueException {
		validateText(role, "user_role");
		this.role = role;
	}
	
	private void validateText(String value, String message) throws InvalidValueException {
		if (!Validator.isValidStrValue(value)) {
			throw new InvalidValueException("invalid_" + message);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}	

}

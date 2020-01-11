package by.epam.bean;

import java.util.Date;

import by.epam.util.Validator;
import by.epam.util.exception.InvalidValueException;

public class Expense {
	
	private int id;
	private int idUser;
	private Date date;
	private long amountCoins;
	private String kind;
	
	public Expense() {
		super();
	}

	public Expense(int id, int idUser, Date date, long amountCoins, String kind) throws InvalidValueException {		
		super();
		
		if (date == null) {
			throw new InvalidValueException("invalid_expense_data");
		}
		if (!Validator.isPositive(amountCoins)) {
			throw new InvalidValueException("invalid_expense_amountCoins");
		}
		validateText(kind, "expense_kind");		
		
		this.id = id;
		this.idUser = idUser;
		this.date = date;
		this.amountCoins = amountCoins;
		this.kind = kind;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) throws InvalidValueException {
		if (date == null) {
			throw new InvalidValueException("invalid_expense_data");
		}
		
		this.date = date;
	}

	public long getAmountCoins() {
		return amountCoins;
	}

	public void setAmountCoins(long amountCoins) throws InvalidValueException {
		if (!Validator.isPositive(amountCoins)) {
			throw new InvalidValueException("invalid_expense_amountCoins");
		}
		
		this.amountCoins = amountCoins;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) throws InvalidValueException {
		validateText(kind, "expense_kind");
		
		this.kind = kind;
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
		result = prime * result + (int) (amountCoins ^ (amountCoins >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + idUser;
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
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
		Expense other = (Expense) obj;
		if (amountCoins != other.amountCoins)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (idUser != other.idUser)
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", idUser=" + idUser + ", date=" + date + ", amountCoins=" + amountCoins
				+ ", kind=" + kind + "]";
	}
	
	
	
	

}

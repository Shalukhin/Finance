package by.epam.bean;

import java.util.Date;

public class Expense {
	
	private int id;
	private int idUser;
	private Date date;
	private long amountCoins;
	private String kind;
	
	public Expense() {
		super();
	}

	public Expense(int id, int idUser, Date date, long amountCoins, String kind) {		
		super();
		
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

	public void setDate(Date date) {				
		this.date = date;
	}

	public long getAmountCoins() {
		return amountCoins;
	}

	public void setAmountCoins(long amountCoins) {		
		this.amountCoins = amountCoins;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {		
		this.kind = kind;
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

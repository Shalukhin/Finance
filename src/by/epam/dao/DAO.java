package by.epam.dao;


import java.util.List;
import by.epam.dao.exception.DAOException;

public interface DAO <T> {

	boolean create (T obj) throws DAOException;
	T read(int id) throws DAOException;
	List<T> read() throws DAOException;
	boolean update (T obj) throws DAOException;
	boolean delete (int id) throws DAOException;
	boolean delete (T obj) throws DAOException;	
	void deleteAll() throws DAOException;
}

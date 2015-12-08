package com.master.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.master.core.exception.MasterException;

public class ConnectionFactory {

	public static Connection getConnection() throws MasterException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new MasterException("Error on loading sql driver: org.postgresql.Driver", e);
		}
		try {
			return DriverManager.getConnection("jdbc:postgresql:pedidos", "postgres", "111111");
		} catch (SQLException e) {
			throw new MasterException("Error on connect database", e);
		}
	}

}

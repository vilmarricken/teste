package com.master.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.Driver;

public class ConnectionFactory {
	
	public static Connection getConnection(){
		try {
			Class.forName(Driver.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:postgresql:pedidos", "postgres", "111111");
	}

}

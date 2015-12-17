package com.master.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.master.core.dao.Dao;
import com.master.core.exception.MasterException;
import com.master.persistence.connection.ConnectionFactory;

public abstract class DaoDefault implements Dao {

	@Override
	public PreparedStatement prepare(String sql) throws MasterException {
		Connection conn;
		PreparedStatement stmt;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			throw new MasterException(e.getMessage() + " - " + sql, e);
		}
		return stmt;
	}

	@Override
	public void closeResultSet(ResultSet rs) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeStatement(Statement stmt) {
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

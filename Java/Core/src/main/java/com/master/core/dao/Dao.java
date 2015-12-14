package com.master.core.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.master.core.exception.MasterException;

public interface Dao {

	PreparedStatement prepare(String sql) throws MasterException;

	void closeResultSet(ResultSet rs) throws MasterException;

	void closeStatement(Statement stmt) throws MasterException;

}

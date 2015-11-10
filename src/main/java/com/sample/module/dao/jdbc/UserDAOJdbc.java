package com.sample.module.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.sample.module.dao.UserDAO;
import com.sample.module.domain.User;
import com.sample.module.util.JdbcUtil;

public class UserDAOJdbc implements UserDAO {
	
	final Logger LOG = Logger.getLogger(getClass());
	
	@Override
	public void insert(User user) {
		
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			Connection connection = DBConnection.current();
			String sql = "insert into users(name, email) values(?, ?)";
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next())
				user.setId(resultSet.getInt(1));
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(statement);
			JdbcUtil.close(resultSet);
		}
		
	}
	
	@Override
	public User getByName(String name) {
		User user = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			Connection connection = DBConnection.current();
			
			String sql = "select * from users where name=?";
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, name);
			
			resultSet = statement.executeQuery();
			if (resultSet.next())
				user = User.MAPPER.convert(resultSet);
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(statement);
			JdbcUtil.close(resultSet);
		}
		
		return user;
	}
	
}

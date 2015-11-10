package com.sample.module.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sample.module.dao.AccountDAO;
import com.sample.module.domain.Account;
import com.sample.module.util.JdbcUtil;

public class AccountDAOJdbc implements AccountDAO {

	private final Logger LOG = Logger.getLogger(getClass());
	
	@Override
	public void insert(Account account) {
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			
			Connection connection = DBConnection.current();
			
			String sql = "insert into accounts(user_id, amount) values (?, ?)";
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setInt(1, account.getUserId());
			statement.setDouble(2, account.getAmount());
			
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				account.setId(resultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(resultSet);
			JdbcUtil.close(statement);
		}
	}

	@Override
	public Account get(int id) {
		Account account = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			
			Connection connection = DBConnection.current();
			
			String sql = "select * from accounts where id=?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				account = Account.MAPPER.convert(resultSet);
			}
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(resultSet);
			JdbcUtil.close(statement);
		}
		
		return account;
	}
	
	@Override
	public List<Account> findByUserId(int userId) {
		List<Account> accounts = new ArrayList<Account>();
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			
			Connection connection = DBConnection.current();
			
			String sql = "select * from accounts where user_id=?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, userId);
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				accounts.add(
						Account.MAPPER.convert(resultSet)
						);
			}
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(resultSet);
			JdbcUtil.close(statement);
		}
		
		return accounts;
	}
	
	@Override
	public void update(Account account) {
		PreparedStatement statement = null;
		try {
			Connection connection = DBConnection.current();
			
			String sql = "update accounts set user_id=?, amount=? where id=?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, account.getUserId());
			statement.setDouble(2, account.getAmount());
			statement.setInt(3, account.getId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		} finally {
			JdbcUtil.close(statement);
		}
	}

}

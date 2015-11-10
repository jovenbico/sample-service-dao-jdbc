package com.sample.module.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetMapper<T> {

	T convert(ResultSet rs) throws SQLException;
	
}

/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.patel.pradeep.headerfooter.flatfilewriter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author Michael Minella
 */
public class CustomerRowMapper implements RowMapper<Employee> {
	@Override
	public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
		System.out.println("CustomerRowMapper.mapRow(): i -> " +i);
		return new Employee(resultSet.getLong("id"),
				resultSet.getString("firstName"),
				resultSet.getString("lastName"),
				resultSet.getDate("birthdate"),
				resultSet.getDouble("salary"),
				resultSet.getString("department"));
	}
}

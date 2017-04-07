package com.Login.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginValidator {
	public boolean validateUser(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			if (connection != null) {
				System.out.println("Connection Established");
				Statement st = connection.createStatement();
				String query = "select * from login where username='" + username + "'";
				ResultSet rs = st.executeQuery(query);
				if (rs.next()) {
					String pwd = rs.getString("password");
					if (password.equals(pwd)) {
						connection.close();
						return true;
					}
				}
				connection.close();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return false;
	}

}

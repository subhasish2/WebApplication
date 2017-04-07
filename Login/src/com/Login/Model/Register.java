package com.Login.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
	Connection connection;
	public boolean insert(User user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
			if (connection != null) {
				System.out.println("Connection Established");
				Statement stmt = connection.createStatement();
				String insert_user = "insert into user values('" + user.getUsername() + "','" + user.getName() + "','"
						+ user.getEmail() + "')";
				int count = stmt.executeUpdate(insert_user);
				String insert_login = "insert into login values('" + user.getUsername() + "','" + user.getPassword()
						+ "')";
				count += stmt.executeUpdate(insert_login);
				connection.close();
				if (count == 2)
					return true;
				return false;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

}

package negi.chintan.app.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import negi.chintan.app.usermanagement.models.Users;

public class UserDAO {
	private String jdbcUrl = "jdbc:mysql://localhost:3306/crudapp";
	private String username = "root";
	private String password = "Chintan12@";
	private static final String insertUserQuery = "insert into users(name,email,country)values(?,?,?)";
	private static final String selectAllUsersQuery = "select * from users";
	private static final String selectByIdUserQuery = "select name,email,country from users where user_id=?";
	private static final String updateUsersQuery = "update users set name=?,email=?,country=? where user_id=?";
	private static final String deleteByIdUsersQuery = "delete from users where user_id=?";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return connection;
	}

	// Insert a new User on the users table
	public void InsertUsers(Users users) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)) {
			preparedStatement.setString(1, users.getName());
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getCountry());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Update an existing User on the users table
	public boolean UpdateUsers(Users users) throws SQLException {
		boolean rowupdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(updateUsersQuery)) {
			preparedStatement.setString(1, users.getName());
			preparedStatement.setString(2, users.getEmail());
			preparedStatement.setString(3, users.getCountry());
			preparedStatement.setInt(4, users.getUser_id());
			rowupdated = preparedStatement.executeUpdate() > 0;
		}
		return rowupdated;
	}

	// Select an User by id on the users table
	public Users SelectUserbyId(int userId) throws SQLException {
		Users user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectByIdUserQuery);) {
			preparedStatement.setInt(1, userId);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new Users(userId, name, email, country);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	// Select All User on the users table
	public List<Users> SelectAllUsers() throws SQLException {
		List<Users> user = new ArrayList<Users>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(selectAllUsersQuery);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				System.out.println(user_id);
				String name = rs.getString("name");
				System.out.println(name);
				String email = rs.getString("email");
				System.out.println(email);
				String country = rs.getString("country");
				System.out.println(country);
				user.add(new Users(user_id, name, email, country));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	// delete by user based on the userId on the users table
	public void deleteUserById(int id) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdUsersQuery);) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

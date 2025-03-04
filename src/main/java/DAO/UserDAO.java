package DAO;

import Models.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=SW10;encrypt=false";
    private String jdbcUsername = "sa";
    private String jdbcPassword = "123456";

    private static final String SELECT_ALL_USERS = "SELECT UserID, FullName, Phone, Email, Address, Gender, Birthday, UserStatus, AccountID FROM Users";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                    rs.getString("UserID"),
                    rs.getString("FullName"),
                    rs.getString("Phone"),
                    rs.getString("Email"),
                    rs.getString("Address"),
                    rs.getString("Gender"),
                    rs.getString("Birthday"),
                    rs.getBoolean("UserStatus"),
                    rs.getString("AccountID")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}

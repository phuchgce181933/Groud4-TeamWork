/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Account;
import Models.GoogleAccount;
import Models.User;
import Util.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author Duy Khanh
 */
public class UserDAO extends DBContext {

    public boolean createUser(String fullName, String phone, String email, String address, String gender, String birthday, String accountID) {
        String sql = "INSERT INTO Users (FullName, Phone, Email, Address, Gender, Birthday, UserStatus, AccountID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setBoolean(7, true); // UserStatus mặc định là true
            ps.setString(8, accountID);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByAccountId(String accountID) {
        User user = null;
        String query = "SELECT * FROM Users WHERE AccountID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setPhone(rs.getString("Phone"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                user.setGender(rs.getString("Gender"));
                user.setBirthday(rs.getString("Birthday"));
                user.setUserStatus(rs.getBoolean("UserStatus"));
            }
        } catch (Exception e) {

        }

        return user;
    }

    public boolean updateUser(String userID, String fullName, String phone, String email, String address, String gender, String birthday) {
        String query = "UPDATE Users SET FullName = ?, Phone = ?, Email = ?, Address = ?, Gender = ?, Birthday = ? WHERE UserID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fullName);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, birthday);
            ps.setString(7, userID);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {

        }

        return false;
    }

    public Account getAccountByUserName(String userName) {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE UserName = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setAccountID(rs.getString("AccountID"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
            }
        } catch (SQLException e) {

        }
        return account;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM Users WHERE Email = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPhone(rs.getString("Phone"));
                user.setAddress(rs.getString("Address"));
            }

            rs.close();
            ps.close();
        } catch (Exception e) {

        }
        return user;
    }

    public void insertGoogleAcc(GoogleAccount googleUser) {
        String sql = "  INSERT INTO Account (UserName,Password,StaffRoleID) VALUES (?,'GG',1);\n"
                + "  DECLARE @last_id int = SCOPE_IDENTITY();\n"
                + "  INSERT INTO Users (AccountID, FullName, Email) VALUES (@last_id, ?, ?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, googleUser.getEmail());
            stmt.setString(2, googleUser.getName());
            stmt.setString(3, googleUser.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public String getAccountIDByEmail(String email) {
        String accountID = null;
        String sql = "SELECT AccountID FROM Users WHERE Email = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    accountID = rs.getString("AccountID");
                }
            }
        } catch (SQLException e) {

        }
        return accountID;
    }

    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE Email = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            
        }
        return false;
    }
    //
    public User getUserByUsername(String username) {
    User user = null;
    String sql = "SELECT * FROM Users WHERE Username = ?";
    
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            user = new User();
            user.setUserID(rs.getString("UserID"));
            user.setFullName(rs.getString("FullName"));
            user.setAccountID(rs.getString("AccountID"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return user;
}

    //get accountbyid
    public Account getAccountById(String accountID) {
        Account account = null;
        String query = "SELECT * FROM Account WHERE AccountID = ?";
        
        try {
             PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                account = new Account();
                account.setAccountID(rs.getString("AccountID"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
                account.setAccountStatus(rs.getBoolean("AccountStatus"));
                account.setStaffRoleID(rs.getString("StaffRoleID"));
            }
        } catch (SQLException e) {
            
        }
        
        return account;
    }

}

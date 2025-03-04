/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Duy Khanh
 */
import Models.Account;
import Util.DBContext;

import java.sql.*;

public class AccountDAO extends DBContext {

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT * FROM Account WHERE UserName = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {

        }
        return false;
    }

    public boolean addAccount(String userName, String password, boolean accountStatus, String staffRoleID) {
        String sql = "INSERT INTO Account (UserName, Password, AccountStatus, StaffRoleID) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setBoolean(3, accountStatus);
            ps.setString(4, staffRoleID);

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Trả về ID của tài khoản mới tạo
                }
            }
        } catch (SQLException e) {

        }
        return false;
    }

    public String createAccount(String UserName, String Password, String StaffRoleID) {
        String sql = "INSERT INTO Account(UserName, Password, AccountStatus, StaffRoleID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, UserName);
            ps.setString(2, Password);
            ps.setBoolean(3, true); // Mặc định kích hoạt
            ps.setString(4, StaffRoleID);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getString(1); // Trả về accountID
            }
        } catch (SQLException e) {

        }
        return null;
    }

//    public Account login(String username, String password) {
//        String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return new Account(
//                        rs.getString("AccountID"),
//                        rs.getString("UserName"),
//                        rs.getString("Password"),
//                        rs.getBoolean("accountStatus"),
//                        rs.getString("StaffRoleID")
//                );
//            }
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }
    public Account CheckLogin(String userName, String password) {
        Account account = null;
        String query = "SELECT * FROM Account WHERE UserName = ? AND Password = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountID(rs.getString("AccountID"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
                account.setStaffRoleID(rs.getString("StaffRoleID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;

    }

    public String getRoleByAccountID(String accountID) {
        String sql = "SELECT sr.RoleName FROM Account a "
                + "LEFT JOIN StaffRole sr ON a.StaffRoleID = sr.RoleID "
                + "WHERE a.AccountID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("RoleName");
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Account getAccountByUserName(String userName) {
        String sql = "SELECT * FROM Account WHERE UserName = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getString("AccountID"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
                account.setAccountStatus(rs.getBoolean("AccountStatus"));
                account.setStaffRoleID(rs.getString("StaffRoleID"));
                return account;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    
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

//    public Account getAccountById(String accountID) {
//        String sql = "SELECT * FROM Account WHERE AccountID = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, accountID);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                return new Account(
//                        rs.getString("AccountID"),
//                        rs.getString("UserName"),
//                        rs.getString("Password"),
//                        rs.getBoolean("AccountStatus"),
//                        rs.getString("StaffRoleID")
//                );
//            }
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }

    public int getAccountIdByUsername(String username) {
        String sql = "SELECT AccountID FROM Account WHERE UserName = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("AccountID");
            }
        } catch (SQLException e) {

        }
        return -1;
    }

    public int getRoleID(String roleName) {
        String sql = "SELECT RoleID FROM StaffRole WHERE RoleName = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("RoleID");
            }
        } catch (SQLException e) {

        }
        return -1;
    }

    public boolean checkGoogleExists(String Email) {
        boolean isValid = false;
        String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = 'GG' ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                isValid = true;

            }
        } catch (SQLException e) {

        }
        return isValid;
    }

    public Account getAccIDByGG(String accountID) {
        Account accGG = new Account();
        String sql = "SELECT * FROM Account WHERE UserName = ? AND Password = 'GG'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                accGG.setAccountID(rs.getString("accountID"));
                accGG.setUserName(rs.getString("userName"));
                accGG.setPassword(rs.getString("password"));
                accGG.setAccountStatus(rs.getBoolean("accountStatus"));
                accGG.setStaffRoleID(rs.getString("StaffRoleID"));
            }
        } catch (SQLException e) {

        }
        return accGG;
    }

    public boolean updatePassword(String accountID, String newPassword) {
        String sql = "UPDATE Account SET Password = ? WHERE AccountID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, accountID);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return false;
    }

    public boolean isGoogleAccountByID(String accountID) {
        String sql = "SELECT COUNT(*) FROM Account WHERE AccountID = ? AND Password = 'GG'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }

}

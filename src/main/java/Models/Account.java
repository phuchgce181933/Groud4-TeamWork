/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Duy Khanh
 */
    public class Account {
        private String accountID;
        private String UserName;
        private String Password;
        private boolean accountStatus;
        private String StaffRoleID;

    public Account() {}

    public Account(String accountID, String UserName, String Password, boolean accountStatus, String StaffRoleID) {
        this.accountID = accountID;
        this.UserName = UserName;
        this.Password = Password;
        this.accountStatus = accountStatus;
        this.StaffRoleID = StaffRoleID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

 

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getStaffRoleID() {
        return StaffRoleID;
    }

    public void setStaffRoleID(String StaffRoleID) {
        this.StaffRoleID = StaffRoleID;
    }


    @Override
    public String toString() {
        return "Account{"
                + "accountID=" + accountID
                + ", userName='" + UserName + '\''
                + ", password='" + Password + '\''
                + ", accountStatus=" + accountStatus
                + ", isRole=" + StaffRoleID
                + '}';
    }
}

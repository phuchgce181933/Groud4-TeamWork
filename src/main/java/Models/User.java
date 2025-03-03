/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Duy Khanh
 */
    public class User {
        private String userID;
        private String fullName;
        private String phone;
        private String email;
        private String address;
        private String gender;
        private String birthday;
        private boolean userStatus;
        private String accountID;


    public User() {}

    public User(String userID, String fullName, String phone, String email, String address, String gender, String birthday, boolean userStatus, String accountID) {
        this.userID = userID;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.birthday = birthday;
        this.userStatus = userStatus;
        this.accountID = accountID;
      
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

 

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }


    @Override
    public String toString() {
        return "User{"
                + "userID=" + userID
                + ", fullName='" + fullName + '\''
                + ", phone='" + phone + '\''
                + ", email='" + email + '\''
                + ", address='" + address + '\''
                + ", gender='" + gender + '\''
                + ", birthday='" + birthday + '\''
                + ", userStatus=" + userStatus
                + ", account=" + accountID
                + '}';
    }
}

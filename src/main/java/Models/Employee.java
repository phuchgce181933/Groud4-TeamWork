/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Duy Khanh
 */
public class Employee {
    private String empID;
    private String empName;
    private String empPhone;
    private String empEmail;
    private boolean empStatus;
    private String accountID;

    public Employee() {}

    public Employee(String empID, String empName, String empPhone, String empEmail, boolean empStatus, String accountID) {
        this.empID = empID;
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
        this.empStatus = empStatus;
        this.accountID = accountID;
    }
    
    // Getter và Setter tương tự Account

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public boolean isEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(boolean empStatus) {
        this.empStatus = empStatus;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", empName='" + empName + '\'' +
                ", empPhone='" + empPhone + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", empStatus=" + empStatus +
                ", account=" + accountID +
                '}';
    }
}


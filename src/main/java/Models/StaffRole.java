/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Duy Khanh
 */
public class StaffRole {
    private String RoleID;
    private String RoleName;

    public StaffRole() {
    }

    public StaffRole(String RoleID, String RoleName) {
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public String getRoleID() {
        return RoleID;
    }

    public void setRoleID(String RoleID) {
        this.RoleID = RoleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
    
}

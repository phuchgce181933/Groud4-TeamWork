/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Gia Phuc - CE181933
 */
public class DBContext {
    private Connection conn;

    public DBContext() {
        try {
            String user = "sa";
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=db5;encrypt=false";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     // test connect
    public void testConnection() {
    try {
        if (conn != null && !conn.isClosed()) {
            System.out.println("Kết nối đến thành công!");
        } else {
            System.out.println("Không thể kết nối đến.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public Connection getConnection() {
        return conn;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Cart;
import Models.Product;
import Models.User;
import Util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Huynh Gia Phuc - CE181933
 */
public class CartDAO extends DBContext {

    public List<Cart> getCartbyAccountId(String accountID) {
        List<Cart> cartItems = new ArrayList<>();
        String userIdQuery = "SELECT UserID FROM Users WHERE AccountID = ?";
        int userId = -1;

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(userIdQuery)) {
            ps.setString(1, accountID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("UserID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return cartItems;
        }

        if (userId == -1) {
            return cartItems;
        }

        // ✅ Cập nhật SQL để lấy `CartID`
        String query = "SELECT Cart.CartID, Cart.UserID, Product.PID, Product.PName, Product.Price, Cart.Quantity, Product.Image "
                + "FROM Cart "
                + "INNER JOIN Product ON Cart.PID = Product.PID "
                + "WHERE Cart.UserID = ?";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setCartID(rs.getInt("CartID")); // 🔹 Lấy CartID
                    cart.setUserID(rs.getInt("UserID"));
                    cart.setPID(rs.getInt("PID"));
                    cart.setPname(rs.getString("PName"));
                    cart.setPrice(rs.getDouble("Price"));
                    cart.setQuantity(rs.getInt("Quantity"));
                    cart.setImage(rs.getString("Image"));
                    cartItems.add(cart);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    //getuid
    public List<Cart> getCartbyUserID(int userID) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT CartID, PID, Pname, Price, Quantity, Image FROM Cart WHERE UserID = ?";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartID(rs.getInt("CartID")); // 🔹 Lấy CartID đúng
                cart.setPID(rs.getInt("PID"));
                cart.setPname(rs.getString("Pname"));
                cart.setPrice(rs.getDouble("Price"));
                cart.setQuantity(rs.getInt("Quantity"));
                cart.setImage(rs.getString("Image"));
                cartList.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartList;
    }

    // 🔹 Xóa sản phẩm khỏi giỏ hàng theo CartID
    public boolean deleteCartItemByCartID(int cartID) {
        String sql = "DELETE FROM Cart WHERE CartID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartID);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateQuantity(int userId, int pid, int quantity) {
        String sql = "UPDATE Cart SET quantity = ? WHERE UserID = ? AND PID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, userId);
            stmt.setInt(3, pid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //add
    public boolean addToCart(int userID, int productID, int quantity) {
    String checkQuery = "SELECT Quantity FROM Cart WHERE UserID = ? AND PID = ?";
    String insertQuery = "INSERT INTO Cart (UserID, PID, Quantity) VALUES (?, ?, ?)";
    String updateQuery = "UPDATE Cart SET Quantity = Quantity + ? WHERE UserID = ? AND PID = ?";

    try (Connection conn = new DBContext().getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

        checkStmt.setInt(1, userID);
        checkStmt.setInt(2, productID);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            // 🔹 Nếu sản phẩm đã có trong giỏ => Cập nhật số lượng
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, userID);
                updateStmt.setInt(3, productID);
                updateStmt.executeUpdate();
            }
        } else {
            // 🔹 Nếu sản phẩm chưa có trong giỏ => Thêm mới
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, userID);
                insertStmt.setInt(2, productID);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
            }
        }
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}

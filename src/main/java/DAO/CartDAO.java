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

    //user--account
    public List<Cart> getCartbyAccountId(String accountID) {
        List<Cart> cartItems = new ArrayList<>();
        String query = "SELECT Cart.CartID, Cart.UserID, Product.PID, Product.PName, Product.Price, Cart.Quantity, Product.Image "
                + "FROM Product "
                + "INNER JOIN Cart ON Product.PID = Cart.PID "
                + "INNER JOIN Users ON Cart.UserID = Users.UserID "
                + "INNER JOIN Account ON Users.AccountID = Account.AccountID "
                + "WHERE Account.AccountID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, accountID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cart cart = new Cart();
                    cart.setCartID(rs.getInt("CartID"));
                    cart.setUserID(rs.getInt("UserID"));
                    cart.setPID(rs.getInt("PID"));  // Bổ sung lấy PID
                    cart.setPname(rs.getString("Pname"));
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

    // Thêm sản phẩm mới vào giỏ hàng
    public boolean addCart(int userId, Product product, int quantity) throws SQLException {
        String checkQuery = "SELECT Quantity FROM Cart WHERE UserID = ? AND PID = ?";
        String updateQuery = "UPDATE Cart SET Quantity = Quantity + ? WHERE UserID = ? AND PID = ?";
        String insertQuery = "INSERT INTO Cart (UserID, PID, Quantity) VALUES (?, ?, ?)";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, product.getPID());

            try ( ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) { // Nếu sản phẩm đã có trong giỏ hàng
                    try ( PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, userId);
                        updateStmt.setInt(3, product.getPID());
                        return updateStmt.executeUpdate() > 0;
                    }
                } else { // Nếu sản phẩm chưa có trong giỏ hàng
                    try ( PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.setInt(2, product.getPID());
                        insertStmt.setInt(3, quantity);
                        return insertStmt.executeUpdate() > 0;
                    }
                }
            }
        }
    }

    // delete cart
    public boolean deleteCartItem(int cartId) throws SQLException {
        String sql = "DELETE FROM Cart WHERE CartID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;
        }
    }
    // Phương thức cập nhật số lượng sản phẩm trong giỏ hàng

    public boolean updateCartItemQuantity(int userId, int productId, int newQuantity) throws SQLException {
        String sql = "UPDATE Cart SET Quantity = ? WHERE UserID = ? AND PID = ?";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newQuantity); // Cập nhật số lượng thay vì cộng dồn
            ps.setInt(2, userId);
            ps.setInt(3, productId);

            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        }
    }

}

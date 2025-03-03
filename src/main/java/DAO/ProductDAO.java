package DAO;

import Models.Product;
import Util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {

    // Lấy danh sách tất cả sản phẩm
    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";

        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setPID(rs.getInt("PID"));
                p.setPName(rs.getString("PName"));
                p.setDetails(rs.getString("Details"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setCatID(rs.getInt("CatID"));
                p.setImage(rs.getString("Image"));
                products.add(p);
            }
        }
        return products;
    }

    // Lấy sản phẩm theo ID
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE PID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("PID"),
                            rs.getString("PName"),
                            rs.getString("Details"),
                            rs.getDouble("Price"),
                            rs.getInt("Quantity"),
                            rs.getInt("CatID"),
                            rs.getString("Image")
                    );
                }
            }
        }
        return null;
    }
    

    // Thêm sản phẩm mới
    public boolean addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Product (PName, Details, Price, Quantity, CatID, Image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getPName());
            ps.setString(2, product.getDetails());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCatID());
            ps.setString(6, product.getImage());

            return ps.executeUpdate() > 0; // Trả về true nếu thêm thành công
        }
    }

    // Cập nhật sản phẩm
    public boolean updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Product SET PName=?, Details=?, Price=?, Quantity=?, CatID=?, Image=? WHERE PID=?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, product.getPName());
            ps.setString(2, product.getDetails());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCatID());
            ps.setString(6, product.getImage());
            ps.setInt(7, product.getPID());

            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        }
    }

    // Xóa sản phẩm
    public boolean deleteProduct(int pid) throws SQLException {
        String sql = "DELETE FROM Product WHERE PID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, pid);
            return ps.executeUpdate() > 0; // Trả về true nếu xóa thành công
        }
    }

    // Kiểm tra CatID có tồn tại hay không
    public boolean isCategoryExists(int catID) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Category WHERE CatID = ?";
        try (Connection conn = new DBContext().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, catID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    //lấy danh sách sản phẩm của một user
    public ArrayList<Product> getProductsByUserId(String userId) throws Exception {
    ArrayList<Product> productList = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE UserID = ?";
    
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, userId);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Product product = new Product(
                rs.getInt("PID"),
                rs.getString("PName"),
                rs.getString("Details"),
                rs.getDouble("Price"),
                rs.getInt("Quantity"),
                rs.getInt("UserID")
            );
            productList.add(product);
        }
    }
    return productList;
}
}

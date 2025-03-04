package DAO;

import Models.Product;
import Util.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phong
 */
public class ProductDAO extends DBContext {

    public List<Product> getAll()  {
        String sql = "select * from product  where ProductStatus = 1";
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product pro = new Product(
                        rs.getInt("PID"),
                        rs.getString("PName"),
                        rs.getString("Details"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"),
                        rs.getInt("CatID"),
                        rs.getString("Image")
                );
                list.add(pro);
            }

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
        return list;
    }

    public boolean addProduct(Product product)  {
        String sql = "INSERT INTO Product (PName, Details, Price, Quantity, CatID, Image) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getPName());
            st.setString(2, product.getDetails());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getQuantity());
            st.setInt(5, product.getCatID());
            st.setString(6, product.getImage());

            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // Lấy sản phẩm theo ID

    public Product getProductById(int pid)  {
        String sql = "SELECT * FROM product WHERE PID = ?";
        Product product = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                product = new Product(
                        rs.getInt("PID"),
                        rs.getString("PName"),
                        rs.getString("details"),
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        rs.getInt("CatID"),
                        rs.getString("image")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // Cập nhật sản phẩm
    public boolean updateProduct(Product p) {
        String sql = "UPDATE product SET PName=?, details=?, price=?, quantity=?, image=? WHERE PID=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPName());
            ps.setString(2, p.getDetails());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getImage());
            ps.setInt(6, p.getPID());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProduct(int PID)  {
        String sql = "UPDATE product SET ProductStatus =0 WHERE PID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, PID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        ProductDAO a = new ProductDAO();
        Product pro = new Product(1, "a", "a", 1, 1, 2, "2");
        System.out.println(a.getAll() + " a");
        if (a.updateProduct(pro)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
    //cart   
    // Cập nhật sản phẩm
    public boolean updateProducts(Product product) throws SQLException {
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
}

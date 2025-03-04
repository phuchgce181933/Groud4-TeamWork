package DAO;

import Models.Category;
import Util.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DBContext { // 🔹 Kế thừa DBContext để sử dụng conn
    // Lấy tất cả danh mục
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try (Statement stmt = conn.createStatement();  // 🔹 Có thể dùng conn vì đã kế thừa
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(new Category(
                    rs.getInt("CatID"),
                    rs.getString("CatName"),
                    rs.getString("Description"),
                    rs.getString("Image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Thêm danh mục
    public void addCategory(String CatName, String Description, String Image) {
        String sql = "INSERT INTO Category (CatName, Description, Image) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) { // 🔹 Sử dụng conn trực tiếp
            stmt.setString(1, CatName);
            stmt.setString(2, Description);
            stmt.setString(3, Image);
            stmt.executeUpdate();
            System.out.println("Category added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật danh mục
    public void updateCategory(int CatID, String CatName, String Description) {
        String sql = "UPDATE Category SET CatName=?, Description=? WHERE CatID=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) { // 🔹 Sử dụng conn trực tiếp
            stmt.setString(1, CatName);
            stmt.setString(2, Description);
            stmt.setInt(3, CatID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Category updated successfully!");
            } else {
                System.out.println("Update failed. No category found with ID: " + CatID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void deleteCategory(int CatID) {
    String updateProductsSQL = "UPDATE Product SET CatID=NULL WHERE CatID=?";
    String deleteCategorySQL = "DELETE FROM Category WHERE CatID=?";

    try {
        conn.setAutoCommit(false); // Bắt đầu transaction

        // Cập nhật CatID của sản phẩm về NULL
        try (PreparedStatement stmt1 = conn.prepareStatement(updateProductsSQL)) {
            stmt1.setInt(1, CatID);
            stmt1.executeUpdate();
        }

        // Xóa danh mục
        try (PreparedStatement stmt2 = conn.prepareStatement(deleteCategorySQL)) {
            stmt2.setInt(1, CatID);
            int rows = stmt2.executeUpdate();
            if (rows > 0) {
                System.out.println("Category deleted successfully!");
            } else {
                System.out.println("Delete failed. No category found with ID: " + CatID);
            }
        }

        conn.commit(); // Hoàn tất transaction
    } catch (SQLException e) {
        try {
            conn.rollback(); // Nếu có lỗi, rollback transaction
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (e.getSQLState().startsWith("23")) {
            System.out.println("Cannot delete category. It is being referenced by another table.");
        } else {
            e.printStackTrace();
        }
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}

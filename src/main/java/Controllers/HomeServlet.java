package Controllers;

import DAO.ProductDAO;
import Models.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 🔹 Lấy danh sách sản phẩm từ database
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAll();

        // 🔹 Lưu danh sách sản phẩm vào request
        request.setAttribute("productList", productList);

        // 🔹 Chuyển hướng đến home.jsp
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

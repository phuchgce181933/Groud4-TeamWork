package Controllers;

import DAO.ProductDAO;
import Models.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditProductServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int pid = Integer.parseInt(request.getParameter("pid"));
            ProductDAO dao = new ProductDAO();
            Product product = dao.getProductById(pid);

            if (product != null) {
                request.setAttribute("product", product);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editproduct.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().println("❌ Sản phẩm không tồn tại!");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("⚠️ Lỗi: ID sản phẩm không hợp lệ!");
        } catch (SQLException ex) {
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.getWriter().println("❌ Lỗi truy vấn cơ sở dữ liệu!");
        }
    }
}

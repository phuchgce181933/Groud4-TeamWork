package Controllers;

import DAO.ProductDAO;
import DAO.UserDAO;
import Models.Product;
import Models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDAO dao = new ProductDAO();
        
        try {
            if ("delete".equals(action)) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                dao.deleteProduct(pid);
                response.sendRedirect("product");
                return;
            }

            // Nếu không có action, hiển thị danh sách sản phẩm
            ArrayList<Product> products = dao.getAllProducts();
            request.setAttribute("productList", products);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDAO dao = new ProductDAO();

        try {
            if ("add".equals(action)) {
                // Lấy dữ liệu từ form
                String pname = request.getParameter("pname");
                String details = request.getParameter("details");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int catID = Integer.parseInt(request.getParameter("catID"));
                String image = request.getParameter("image");

                // Kiểm tra CatID có tồn tại không
                if (!dao.isCategoryExists(catID)) {
                    response.getWriter().println("<script>");
                    response.getWriter().println("alert('Lỗi: Mã danh mục không hợp lệ!');");
                    response.getWriter().println("setTimeout(function() { window.location.href = 'product'; }, 3000);");
                    response.getWriter().println("</script>");
                    return;
                }

                // Tạo đối tượng Product và thêm vào DB
                Product product = new Product(0, pname, details, price, quantity, catID, image);
                dao.addProduct(product);
                response.sendRedirect("product");
                return;

            } else if ("delete".equals(action)) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                dao.deleteProduct(pid);
                response.sendRedirect("product");

            } else if ("edit".equals(action)) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                String pname = request.getParameter("pname");
                String details = request.getParameter("details");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                int catID = Integer.parseInt(request.getParameter("catID"));
                String image = request.getParameter("image");

                Product product = new Product(pid, pname, details, price, quantity, catID, image);
                boolean success = dao.updateProduct(product);
                if (success) {
                    response.sendRedirect("product");
                } else {
                    response.getWriter().println("Cập nhật thất bại!");
                }
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Product Servlet";
    }

}
//

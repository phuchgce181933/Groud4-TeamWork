/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAO.ProductDAO;
import Models.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh Gia Phuc - CE181933
 */
public class HomeServlet extends HttpServlet {
   
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

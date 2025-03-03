/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author phong
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB threshold
                 maxFileSize = 1024 * 1024 * 10,       // Max file size: 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // Max request size: 50MB
public class editProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("PID"));

        ProductDAO proDAO = new ProductDAO();

        Product pro = proDAO.getProductById(pid);
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategories();

        if (pro != null) {
            request.setAttribute("data", pro);
            request.setAttribute("categoryList", categoryList);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        } else {
            response.sendRedirect("ViewProduct.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    
    String name = request.getParameter("name");
    String details = request.getParameter("details");
    String priceStr = request.getParameter("price");
    String quantityStr = request.getParameter("quantity");
    Part image = request.getPart("image");
    String categoryIDStr = request.getParameter("catID");
    int pid = Integer.parseInt(request.getParameter("PID"));

    ProductDAO productDAO = new ProductDAO();
    Product existingProduct = productDAO.getProductById(pid);

    if (existingProduct == null) {
        request.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        return;
    }

    if (name == null || details == null || priceStr == null || quantityStr == null || categoryIDStr == null ||
        name.trim().isEmpty() || details.trim().isEmpty() || priceStr.trim().isEmpty() || quantityStr.trim().isEmpty()) {
        request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin sản phẩm.");
        request.setAttribute("data", existingProduct);
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        return;
    }

    try {
        int categoryID = Integer.parseInt(categoryIDStr.trim());
        double price = Double.parseDouble(priceStr.trim());
        int quantity = Integer.parseInt(quantityStr.trim());

        if (price <= 0 || quantity < 0) {
            request.setAttribute("errorMessage", "Giá và số lượng phải là số hợp lệ.");
            request.setAttribute("data", existingProduct);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }

        // Handle image upload
        String imageName = existingProduct.getImage(); // Default to old image
        if (image != null && image.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            imageName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            String imagePath = uploadPath + File.separator + imageName;
            image.write(imagePath);
        }

        // Create updated product object
        Product updatedProduct = new Product(pid, name, details, price, quantity, categoryID, imageName);

        // Update product in database
        if (productDAO.updateProduct(updatedProduct)) {
            response.sendRedirect("ViewProduct");
        } else {
            request.setAttribute("errorMessage", "Không thể cập nhật sản phẩm, vui lòng thử lại.");
            request.setAttribute("data", existingProduct);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        }

    } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Giá và số lượng phải là số hợp lệ.");
        request.setAttribute("data", existingProduct);
        request.getRequestDispatcher("editProduct.jsp").forward(request, response);
    }
}


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

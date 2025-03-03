package Controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Category;
import Model.Product;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet handling adding a new product.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB threshold
                 maxFileSize = 1024 * 1024 * 10,       // Max file size: 10MB
                 maxRequestSize = 1024 * 1024 * 50)    // Max request size: 50MB
public class addProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> categoryList = categoryDAO.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Get form parameters
        String name = request.getParameter("name");
        String details = request.getParameter("details");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String categoryIDStr = request.getParameter("category");
        Part image = request.getPart("image");

        // Validate mandatory fields
        if (name == null || name.trim().isEmpty() ||
            details == null || details.trim().isEmpty() ||
            priceStr == null || priceStr.trim().isEmpty() ||
            quantityStr == null || quantityStr.trim().isEmpty() ||
            categoryIDStr == null || categoryIDStr.trim().isEmpty() ||
            image == null) {

            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            return;
        }

        try {
            int categoryID = Integer.parseInt(categoryIDStr.trim());
            double price = Double.parseDouble(priceStr.trim());
            int quantity = Integer.parseInt(quantityStr.trim());

            if (price <= 0 || quantity <= 0) {
                request.setAttribute("errorMessage", "Giá và số lượng phải lớn hơn 0.");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                return;
            }

            // Handle image upload
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String imageName = Paths.get(image.getSubmittedFileName()).getFileName().toString();
            String imagePath = uploadPath + File.separator + imageName;
            image.write(imagePath);

            // Create Product object
            Product product = new Product(0, name, details, price, quantity, categoryID, imageName);

            // Save product in database
            ProductDAO dao = new ProductDAO();
            if (dao.addProduct(product)) {
                response.sendRedirect("ViewProduct"); // Redirect to product list
            } else {
                request.setAttribute("errorMessage", "Không thể thêm sản phẩm, vui lòng thử lại.");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Giá và số lượng phải là số hợp lệ.");
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for adding new products";
    }
}

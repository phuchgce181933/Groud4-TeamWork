package Controllers;

import DAO.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Models.Category;

//@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("category_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            categoryDAO.addCategory(
                request.getParameter("CatName"),
                request.getParameter("Description"),
                request.getParameter("Image")
            );
        } else if ("edit".equals(action)) {
            categoryDAO.updateCategory(
                Integer.parseInt(request.getParameter("CatID")),
                request.getParameter("CatName"),
                request.getParameter("Description")
            );
        } else if ("delete".equals(action)) {
            categoryDAO.deleteCategory(Integer.parseInt(request.getParameter("CatID")));
        }

        response.sendRedirect("CategoryServlet");
    }
}

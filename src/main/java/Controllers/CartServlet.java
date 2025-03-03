package Controllers;

import DAO.CartDAO;
import DAO.ProductDAO;
import Models.Cart;
import Models.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
         

        if ("delete".equals(action)) {
            handleDelete(request, response);
            return;
        }

        // Lấy userID từ cookie
        Cookie[] cookies = request.getCookies();
        String accountID = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    accountID = cookie.getValue();
                    break;
                }
            }
        }

        // Nếu không có accountID, yêu cầu đăng nhập
        if (accountID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Lấy giỏ hàng theo AccountID
            CartDAO cartDAO = new CartDAO();
            List<Cart> cartItems = cartDAO.getCartbyAccountId(accountID);

            int totalItems = (cartItems != null) ? cartItems.size() : 0; // Đếm số sản phẩm khác nhau

            // Đặt totalItems vào request để JSP có thể lấy
            request.setAttribute("totalItems", totalItems);

            if (cartItems == null || cartItems.isEmpty()) {
                request.setAttribute("message", "Giỏ hàng của bạn đang trống.");
            } else {
                request.setAttribute("cartItems", cartItems);
            }

            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi tải giỏ hàng.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    // Xử lý thêm sản phẩm vào giỏ hàng
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy userID từ cookie
            String userID = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userName")) {
                        userID = cookie.getValue();
                        break;
                    }
                }
            }

            if (userID == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            int userId = Integer.parseInt(userID);
            String action = request.getParameter("action");

            CartDAO cartDAO = new CartDAO();

            if ("delete".equals(action)) {
                handleDelete(request, response);
                return;
            } else if ("update".equals(action)) {
                String productIdParam = request.getParameter("pid");
                String quantityParam = request.getParameter("quantity");

                if (productIdParam == null || quantityParam == null) {
                    request.setAttribute("error", "Thiếu thông tin sản phẩm hoặc số lượng.");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }

                int productId = Integer.parseInt(productIdParam);
                int newQuantity = Integer.parseInt(quantityParam);

                if (newQuantity <= 0) {
                    request.setAttribute("error", "Số lượng không hợp lệ.");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }

                boolean success = cartDAO.updateCartItemQuantity(userId, productId, newQuantity);

                if (success) {
                    response.sendRedirect("home");
                } else {
                    request.setAttribute("error", "Không thể cập nhật số lượng.");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
                return;
            }

            // Xử lý thêm sản phẩm vào giỏ hàng
            String productIdParam = request.getParameter("pid");
            String quantityParam = request.getParameter("quantity");

            if (productIdParam == null || quantityParam == null) {
                request.setAttribute("error", "Thiếu thông tin sản phẩm hoặc số lượng.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }

            int productId = Integer.parseInt(productIdParam);
            int quantity = Integer.parseInt(quantityParam);

            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);

            if (product == null) {
                request.setAttribute("error", "Sản phẩm không tồn tại.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }

            //CartDAO cartDAO = new CartDAO();
            boolean success = cartDAO.addCart(userId, product, quantity);

            if (success) {
                response.sendRedirect("home");
            } else {
                request.setAttribute("error", "Không thể thêm vào giỏ hàng.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý giỏ hàng.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String cartIdParam = request.getParameter("pid"); // Kiểm tra có phải là CartID không
            if (cartIdParam == null) {
                request.setAttribute("error", "Thiếu thông tin sản phẩm cần xóa.");
                response.sendRedirect("cart");
                return;
            }

            int cartId = Integer.parseInt(cartIdParam);

            CartDAO cartDAO = new CartDAO();
            boolean success = cartDAO.deleteCartItem(cartId);

            if (success) {
                response.sendRedirect("home");
            } else {
                request.setAttribute("error", "Không thể xóa sản phẩm.");
                response.sendRedirect("cart");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi xử lý xóa sản phẩm.");
            response.sendRedirect("cart");
        }
    }

}

package Controllers;

import DAO.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountID = getAccountIDFromCookies(request);

        if (accountID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAO();
        request.setAttribute("cartList", cartDAO.getCartbyAccountId(accountID));
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            handleUpdate(request, response);
        } else if ("delete".equals(action)) {
            handleDelete(request, response);
        } else {
            response.sendRedirect("cart");
        }
    }

    // 🔹 Cập nhật số lượng sản phẩm trong giỏ hàng
    // 🔹 Hàm cập nhật số lượng sản phẩm trong giỏ hàng
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accountID = getAccountIDFromCookies(request);
            String productIdParam = request.getParameter("pid");
            String quantityParam = request.getParameter("quantity");

            if (accountID == null || productIdParam == null || quantityParam == null) {
                System.out.println("Lỗi: Thiếu accountID, pid hoặc quantity");
                response.sendRedirect("cart");
                return;
            }

            int userID = Integer.parseInt(accountID);
            int productID = Integer.parseInt(productIdParam);
            int quantity = Integer.parseInt(quantityParam);

            if (quantity <= 0) {
                System.out.println("Lỗi: Số lượng phải lớn hơn 0");
                response.sendRedirect("cart");
                return;
            }

            System.out.println("Cập nhật giỏ hàng: UserID = " + userID + ", ProductID = " + productID + ", Quantity = " + quantity); // Debug

            CartDAO cartDAO = new CartDAO();
            cartDAO.updateQuantity(userID, productID, quantity);

            response.sendRedirect("cart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cart");
        }
    }

    // 🔹 Xóa sản phẩm khỏi giỏ hàng theo CartID
    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String cartIdParam = request.getParameter("cartID");

            if (cartIdParam == null || cartIdParam.isEmpty()) {
                System.out.println("Lỗi: Không tìm thấy cartID");
                response.sendRedirect("cart");
                return;
            }

            int cartID = Integer.parseInt(cartIdParam);

            System.out.println("🔹 Xóa sản phẩm: CartID = " + cartID);

            CartDAO cartDAO = new CartDAO();
            boolean success = cartDAO.deleteCartItemByCartID(cartID);

            if (success) {
                System.out.println("✅ Xóa sản phẩm thành công!");
            } else {
                System.out.println("❌ Không thể xóa sản phẩm!");
            }

            response.sendRedirect("cart");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cart");
        }
    }

    // 🔹 Lấy AccountID từ Cookie
    private String getAccountIDFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accountID")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}

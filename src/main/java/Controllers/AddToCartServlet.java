package Controllers;

import DAO.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountID = getAccountIDFromCookies(request);
        String productIDParam = request.getParameter("pid");
        String quantityParam = request.getParameter("quantity");

        if (accountID == null || productIDParam == null || quantityParam == null) {
            System.out.println("Lỗi: Thiếu accountID, pid hoặc quantity");
            response.sendRedirect("products.jsp");
            return;
        }

        int userID = Integer.parseInt(accountID);
        int productID = Integer.parseInt(productIDParam);
        int quantity = Integer.parseInt(quantityParam);

        if (quantity <= 0) {
            System.out.println("Lỗi: Số lượng phải lớn hơn 0");
            response.sendRedirect("products.jsp");
            return;
        }

        CartDAO cartDAO = new CartDAO();
        boolean success = cartDAO.addToCart(userID, productID, quantity);

        if (success) {
            System.out.println("✅ Thêm vào giỏ hàng thành công!");
        } else {
            System.out.println("❌ Lỗi khi thêm vào giỏ hàng!");
        }

        response.sendRedirect("cart");
    }

    // 🔹 Hàm lấy `AccountID` từ cookie
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.AccountDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Duy Khanh
 */
public class registerServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
    response.setCharacterEncoding("UTF-8");

    String fullName = request.getParameter("CusName");
    String username = request.getParameter("userName");
    String password = request.getParameter("password");
    String birthday = request.getParameter("CusBirthday");
    String gender = request.getParameter("CusGender");
    String email = request.getParameter("CusEmail");
    String address = request.getParameter("CusAddress");
    String phone = request.getParameter("CusPhone");

    if (fullName == null || fullName.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Họ và tên không được để trống.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Tên đăng nhập không được bỏ trống.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (password == null || password.length() < 6 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            request.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 6 ký tự, gồm chữ, số, ký tự đặc biệt và chữ in hoa.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            request.setAttribute("errorMessage", "Định dạng email không hợp lệ.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (phone != null && !phone.isEmpty() && !phone.matches("^(?:\\+84|84|0)[3|5|7|8|9][0-9]{8}$")) {
            request.setAttribute("errorMessage", "Số điện thoại không hợp lệ.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (birthday == null || birthday.isEmpty()) {
            request.setAttribute("errorMessage", "Ngày sinh không được bỏ trống.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthday, formatter);
            LocalDate today = LocalDate.now();

            if (birthDate.isAfter(today)) {
                request.setAttribute("errorMessage", "Ngày sinh không được lớn hơn ngày hiện tại.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Định dạng ngày không hợp lệ.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
    
    
    AccountDAO accountDAO = new AccountDAO();
    UserDAO userDAO = new UserDAO();

    // Kiểm tra tên đăng nhập trước
    if (accountDAO.checkUsernameExists(username)) {
        request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Kiểm tra email trước khi tạo tài khoản
    if (userDAO.checkEmailExists(email)) {
        request.setAttribute("errorMessage", "Email đã được đăng ký!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }

    // Nếu email hợp lệ, mới tạo tài khoản
    int staffRoleID = accountDAO.getRoleID("User");
    String accountID = accountDAO.createAccount(username, password, String.valueOf(staffRoleID));

    if (accountID == null) {
        request.setAttribute("errorMessage", "Lỗi khi tạo tài khoản!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
        return;
    }
    // Tạo user
    boolean isUserCreated = userDAO.createUser(fullName, phone, email, address, gender, birthday, accountID);

    if (isUserCreated) {
        response.sendRedirect("login.jsp");
    } else {
        request.setAttribute("errorMessage", "Lỗi khi tạo người dùng!");
        request.getRequestDispatcher("register.jsp").forward(request, response);
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

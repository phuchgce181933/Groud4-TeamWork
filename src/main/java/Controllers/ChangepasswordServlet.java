/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAO.AccountDAO;
import DAO.UserDAO;
import Models.Account;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Khanh
 */
public class ChangepasswordServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ChangepasswordServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangepasswordServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String accountID = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accountID")) {
                    accountID = cookie.getValue();
                    break;
                }
            }
        }

        if (accountID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UserDAO userDAO = new UserDAO();
        Account account = userDAO.getAccountById(accountID);

        if (account != null) {
            request.setAttribute("account", account);
            RequestDispatcher dispatcher = request.getRequestDispatcher("changepassword.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String accountID = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accountID")) {
                    accountID = cookie.getValue();
                    break;
                }
            }
        }

        if (accountID == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (oldPassword == null || oldPassword.trim().isEmpty()) {
            request.setAttribute("error", "Mật khẩu cũ không được để trống!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }

        if (newPassword == null || newPassword.length() < 6 || !newPassword.matches(".*[A-Z].*") || !newPassword.matches(".*[a-z].*") || !newPassword.matches(".*\\d.*") || !newPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            request.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 6 ký tự, gồm chữ, số, ký tự đặc biệt và chữ in hoa.");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }
        
       AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountById(accountID);

        if (account == null || !account.getPassword().equals(oldPassword)) {
            request.setAttribute("error", "Mật khẩu cũ không chính xác!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }

        boolean isUpdated = accountDAO.updatePassword(accountID, newPassword);
        if (isUpdated) {
            response.sendRedirect("viewProfileServlet");
        } else {
            request.setAttribute("error", "Cập nhật mật khẩu thất bại!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        }
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

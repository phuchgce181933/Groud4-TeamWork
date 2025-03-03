/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAO.UserDAO;
import Models.Account;
import Models.User;
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
public class editProfileServlet extends HttpServlet {

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
            out.println("<title>Servlet editProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfileServlet at " + request.getContextPath() + "</h1>");
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
    Cookie[] cookies = request.getCookies();
    String userID = null;

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
        UserDAO userDAO = new UserDAO();
  
          User user = userDAO.getUserByAccountId(userID);

        if (user != null) {
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editProfile.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
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
    Cookie[] cookies = request.getCookies();
    String userID = null;

    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userName")) {
                userID = cookie.getValue();
                break;
            }
        }
    }

    if (userID != null) {
        UserDAO userDAO = new UserDAO();
          User user = userDAO.getUserByAccountId(userID);

        if (user != null) {
            String id = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");

               if (fullName == null || fullName.trim().isEmpty()) {
                request.setAttribute("message", "Họ và tên không được để trống.");
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
                return;
            }
            
            if (phone == null || !phone.matches("\\d{10,11}")) {
                request.setAttribute("message", "Số điện thoại không hợp lệ. Số điện thoại phải có 10-11 chữ số.");
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
                return;
            }

            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                request.setAttribute("message", "Định dạng email không hợp lệ.");
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
                return;
            }

            if (birthday != null && !birthday.matches("\\d{4}-\\d{2}-\\d{2}")) {
                request.setAttribute("message", "Định dạng ngày không hợp lệ. Sử dụng YYYY-MM-DD.");
                request.getRequestDispatcher("/editProfile.jsp").forward(request, response);
                return;
            }
            
         
            user.setUserID(userID);
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setEmail(email);
            user.setAddress(address);
            user.setGender(gender);
            user.setBirthday(birthday);

            
            
            boolean isUpdated = userDAO.updateUser(id, fullName, phone, email, address, gender, birthday);

            if (isUpdated) {
                request.setAttribute("message", "Profile updated successfully.");
            } else {
                request.setAttribute("message", "Failed to update profile.");
            }

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewProfile.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    } else {
        response.sendRedirect("login.jsp");
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

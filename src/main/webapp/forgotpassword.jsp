<%-- 
    Document   : forgotpassword
    Created on : Feb 22, 2025, 6:33:40 PM
    Author     : Duy Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quên Mật Khẩu</title>
    <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f89a1c;
        }
        .btn-primary {
            background-color: #f89a1c;
            border-color: #f89a1c;
        }
        .btn-primary:hover {
            background-color: #d87f17;
            border-color: #d87f17;
        }
    </style>
<script>
    function validatePassword() {
        let password = document.getElementById("newPassword").value;
        let message = document.getElementById("passwordMessage");

        // Biểu thức kiểm tra ký tự đặc biệt và chữ in hoa
        let specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
        let uppercaseRegex = /[A-Z]/;

        if (password.length < 6) {
            message.innerHTML = "Mật khẩu phải có ít nhất 6 ký tự.";
            message.style.color = "red";
            return false;
        } else if (!specialCharRegex.test(password)) {
            message.innerHTML = "Mật khẩu phải chứa ít nhất một ký tự đặc biệt.";
            message.style.color = "red";
            return false;
        } else if (!uppercaseRegex.test(password)) {
            message.innerHTML = "Mật khẩu phải chứa ít nhất một chữ in hoa.";
            message.style.color = "red";
            return false;
        } else {
            message.innerHTML = "";
            return true;
        }
    }
</script>
</head>
<body>
    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6">
                    <div class="card border-0 shadow-sm rounded-4">
                        <div class="card-body p-4">
                            <h3 class="text-center">Quên Mật Khẩu</h3>

                            <!-- Hiển thị thông báo -->
                            <c:if test="${not empty mess}">
                                <p class="text-danger text-center mt-3">${mess}</p>
                            </c:if>

                            <form action="ForgotpasswordServlet" method="POST" onsubmit="return validatePassword();">
                                <c:choose>
                                    <c:when test="${empty emailVerified}">
                                        <!-- Form nhập email -->
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                            <div class="input-group">
                                                <input type="email" class="form-control" name="email" id="email" required>
                                                <button type="submit" name="action" value="checkEmail" class="btn btn-secondary">Kiểm tra Email</button>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- Form đặt lại mật khẩu -->
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email</label>
                                            <input type="email" class="form-control" value="${emailVerified}" disabled>
                                        </div>
                                        <input type="hidden" name="email" value="${emailVerified}">

                                        <div class="mb-3">
                                            <label for="newPassword" class="form-label">Mật khẩu mới</label>
                                            <input type="password" class="form-control" name="newPassword" id="newPassword" required>
                                        </div>
                                        <p id="passwordMessage" class="text-danger"></p> <!-- Hiển thị lỗi -->
                                        <div class="mb-3">
                                            <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                                            <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required>
                                        </div>
                                        <div class="d-grid">
                                            <button type="submit" name="action" value="resetPassword" class="btn btn-primary">Đặt lại mật khẩu</button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </form>

                            <div class="text-center mt-4">
                                <a href="login.jsp" class="text-decoration-none">Đăng nhập</a> | 
                                <a href="register.jsp" class="text-decoration-none">Đăng ký</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

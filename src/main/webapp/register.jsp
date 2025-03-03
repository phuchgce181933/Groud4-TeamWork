<%-- 
    Document   : register
    Created on : Feb 15, 2025, 2:09:47 PM
    Author     : Duy Khanh
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration Form</title>
        <style>
            body {
                background: linear-gradient(135deg, #000, #ff6600);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                font-family: Arial, sans-serif;
            }
            .container {
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
                width: 400px;
            }
            h2 {
                text-align: center;
                color: #ff6600;
            }
            .form-group {
                margin-bottom: 10px;
            }
            label {
                display: block;
                font-weight: bold;
                color: #000;
            }
            input, select {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .btn-submit {
                width: 100%;
                padding: 10px;
                background: #ff6600;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
            }
            .btn-submit:hover {
                background: #cc5200;
            }
            .text-danger {
                color: red;
            }
        </style>
        
    </head>
    <body>
        <div class="container">
            <h2>Registration Form</h2>
            <form action="registerServlet" method="post">
                <div class="form-group">
                    <label for="CusName">Full Name</label>
                    <input type="text" id="CusName" name="CusName" required>
                </div>
                <div class="form-group">
                    <label for="userName">Username</label>
                    <input type="text" id="userName" name="userName" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Confirm Password</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>
                <div class="form-group">
                    <label for="CusBirthday">Birthday</label>
                    <input type="date" id="CusBirthday" name="CusBirthday" required>
                </div>
                <div class="form-group">
                    <label for="CusGender">Gender</label>
                    <select id="CusGender" name="CusGender" required>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="CusEmail">Email</label>
                    <input type="email" id="CusEmail" name="CusEmail" required>
                </div>
                <div class="form-group">
                    <label for="CusAddress">Address</label>
                    <input type="text" id="CusAddress" name="CusAddress" required>
                </div>
                <div class="form-group">
                    <label for="CusPhone">Phone Number</label>
                    <input type="text" id="CusPhone" name="CusPhone">
                </div>


                <div id="error-message" class="text-danger mt-3"></div>

                <c:if test="${not empty errorMessage}">
                    <div class="text-danger mt-3">
                        ${errorMessage}
                    </div>
                </c:if>
                <button type="submit" class="btn-submit">Submit</button>
            </form>
        </div>
    </body>
</html>


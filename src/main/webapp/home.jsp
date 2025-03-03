<%-- 
    Document   : home
    Created on : Feb 15, 2025, 2:07:45 PM
    Author     : Duy Khanh
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang Chủ</title>
        <style>
            .profile-icon {
                width: 50px;
                height: 50px;
                cursor: pointer;
            }
            .logout-btn {
                display: inline-block;
                padding: 8px 16px;
                background-color: red;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-left: 20px;
            }
            .profile-btn {
                display: inline-block;
                padding: 8px 16px;
                background-color: lightskyblue;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-left: 20px;
            }
        </style>
    </head>
    <body>
        <h1>Chào mừng đến với Trang Chủ</h1>


        <a href="viewProfileServlet" class="profile-btn" >Profile </a>


        <a href="LogoutServlet" class="logout-btn">Đăng Xuất</a>
    </body>
</html>


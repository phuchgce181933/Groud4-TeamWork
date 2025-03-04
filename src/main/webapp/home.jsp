<%-- 
    Document   : home
    Created on : Feb 15, 2025, 2:07:45 PM
    Author     : Duy Khanh
--%>
<%@page import="java.util.List"%>
<%@page import="Models.Product"%>
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <h1>Chào mừng đến với Trang Chủ</h1>
        <div class="container">
        <h1 class="text-center mt-4">Danh Sách Sản Phẩm</h1>
        <div class="row">
            <%
                List<Product> productList = (List<Product>) request.getAttribute("productList");
                if (productList != null) {
                    for (Product product : productList) {
            %>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="<%= product.getImage() %>" class="card-img-top" alt="<%= product.getPName() %>">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getPName() %></h5>
                        <p class="card-text"><%= product.getDetails() %></p>
                        <p class="card-text"><strong>Giá: </strong><%= product.getPrice() %> VNĐ</p>
                        <form action="addcart" method="post">
                            <input type="hidden" name="pid" value="<%= product.getPID() %>">
                            <input type="number" name="quantity" value="1" min="1" class="form-control mb-2">
                            <button type="submit" class="btn btn-primary">🛒 Thêm vào giỏ hàng</button>
                        </form>
                    </div>
                </div>
            </div>
            <%  
                    }
                } else {
            %>
            <p class="text-center">Không có sản phẩm nào.</p>
            <% } %>
        </div>
    </div>

        <a href="viewProfileServlet" class="profile-btn" >Profile </a>
        <a href="cart" class="cart-btn" >Cart </a>
        <a href="LogoutServlet" class="logout-btn">Đăng Xuất</a>
        
    </body>
</html>


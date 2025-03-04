<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*,javax.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String pid = request.getParameter("PID");
    String name = "";
    String details = "";
    int price = 0;
    int quantity = 0;
    String image = "";

    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "password");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE PID=?");
        ps.setString(1, pid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            name = rs.getString("PName");
            details = rs.getString("details");
            price = rs.getInt("price");
            quantity = rs.getInt("quantity");
            image = rs.getString("image");
        }

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chỉnh sửa sản phẩm</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                text-align: center;
            }
 .category-select {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: white;
            }
            .container {
                max-width: 600px;
                margin: 40px auto;
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            }

            h2 {
                color: #ff6600;
            }

            form {
                display: flex;
                flex-direction: column;
            }

            label {
                text-align: left;
                margin: 10px 0 5px;
                font-weight: bold;
            }

            input, textarea {
                width: 100%;
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            button {
                background-color: #ff6600;
                color: white;
                border: none;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                opacity: 0.8;
            }

            .back {
                display: inline-block;
                margin-top: 10px;
                color: #ff6600;
                text-decoration: none;
            }

            .back:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Chỉnh sửa sản phẩm</h2>
            <form action="editproduct" method="post"  enctype="multipart/form-data">
                <input type="hidden" name="PID" value="${data.PID}">

                <label for="name">Tên sản phẩm:</label>
                <input type="text" name="name" value="${data.PName}" required>

                <select name="catID" class="category-select">
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.catID}" ${category.catID == data.catID ? 'selected' : ''}>
                            ${category.catName}
                        </option>

                    </c:forEach>
                </select> <br>

                <label for="details">Mô tả:</label>
                <textarea name="details" required>${data.details}</textarea>

                <label for="price">Giá (VND):</label>
                <input type="number" name="price" value="${data.price}" required>

                <label for="quantity">Số lượng:</label>
                <input type="number" name="quantity" value="${data.quantity}" required>

                <label for="image">Hình ảnh:</label>
                <input type="file" name="image" accept="image/*" >

                <button type="submit">Cập nhật</button>
            </form>
            <a href="viewproduct" class="back">← Quay lại danh sách</a>
        </div>
        <c:if test="${not empty errorMessage}">
            <p style="color:red;">${errorMessage}</p>
        </c:if>

    </body>
</html>

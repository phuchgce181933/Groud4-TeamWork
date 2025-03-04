<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách sản phẩm</title>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: Arial, sans-serif;
            }

            body {
                background-color: #f8f9fa;
                padding: 20px;
            }

            h2 {
                text-align: center;
                color: #ff6600;
                margin-bottom: 20px;
            }

            .container {
                width: 100%;
                max-width: 1200px;
                margin: auto;
                text-align: center;
            }

            .add-btn {
                display: inline-block;
                background-color: #ff6600;
                color: white;
                padding: 10px 15px;
                text-decoration: none;
                border-radius: 5px;
                font-weight: bold;
                font-size: 16px;
                margin-bottom: 10px;
            }

            .add-btn i {
                margin-right: 5px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background: white;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
            }

            th, td {
                padding: 12px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #ff6600;
                color: white;
                text-transform: uppercase;
            }

            tr:hover {
                background-color: #ffe6cc;
            }

            img {
                width: 80px;
                height: auto;
                border-radius: 5px;
                border: 1px solid #ddd;
                transition: transform 0.3s ease-in-out;
            }

            img:hover {
                transform: scale(1.1);
            }

            .btn {
                padding: 8px 12px;
                text-decoration: none;
                border: none;
                color: white;
                font-size: 14px;
                cursor: pointer;
                border-radius: 4px;
                display: inline-flex;
                align-items: center;
            }

            .btn-edit {
                background-color: #ff9900;
            }

            .btn-delete {
                background-color: #ff3300;
            }

            .btn i {
                margin-right: 5px;
            }

            .btn:hover {
                opacity: 0.8;
            }
        </style>

        <!-- Thêm icon từ FontAwesome -->
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách Sản phẩm</h2>
            <a href="addproduct" class="add-btn">
                <i class="fas fa-plus"></i> Thêm sản phẩm
            </a>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Mô tả</th>
                <th>Giá</th>
                <th>Số lượng</th>
                <th>Hình ảnh</th>
                <th>Hành động</th>
            </tr>
            <c:forEach items="${data}" var="p">
                <tr>
                    <td>${p.PID}</td>
                    <td>${p.PName}</td>
                    <td>${p.details}</td>
                    <td><strong style="color:#ff6600;">${p.price} VND</strong></td>
                    <td>${p.quantity}</td>
                    <td><img src="${pageContext.request.contextPath}/uploads/${p.image}" alt="Product Image"/></td>
                    <td>
                        <a href="editproduct?PID=${p.PID}" class="btn btn-edit">
                            <i class="fas fa-edit"></i> Sửa
                        </a>
                        <a href="deleteproduct?PID=${p.PID}" class="btn btn-delete"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
                            <i class="fas fa-trash-alt"></i> Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${not empty errorMessage}">
            <div style="color: red; text-align: center; font-weight: bold;">
                ${errorMessage}
            </div>
        </c:if>
    </body>
</html>
